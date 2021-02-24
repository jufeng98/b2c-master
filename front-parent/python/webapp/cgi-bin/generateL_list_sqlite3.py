import glob
import os
import sqlite3
import sys

import athletemodel
import yate

if not os.path.exists("target/coach_data.sqlite"):
    if not os.path.exists("target"):
        os.mkdir("target")
    connection = sqlite3.connect("target/coach_data.sqlite")
    cursor = connection.cursor()
    cursor.execute(
        """
        CREATE TABLE athletes(
          id INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL,
          name TEXT NOT NULL,
          dob DATE NOT NULL)
        """
    )
    cursor.execute(
        """
        CREATE TABLE timing_data(
          athlete_id INTEGER NOT NULL,
          value TEXT NOT NULL,
          FOREIGN KEY (athlete_id) REFERENCES athletes)
        """
    )

    files = None
    if os.path.exists("data/james.txt"):
        files = glob.glob("data/*.txt")
    if os.path.exists("../data/james.txt"):
        files = glob.glob("../data/*.txt")
    plays = athletemodel.put_to_store(files)

    for athlete_name in plays:
        name = plays[athlete_name].name
        dob = plays[athlete_name].dob
        cursor.execute("INSERT INTO athletes (name, dob) VALUES (? ,?)", (name, dob))
        cursor.execute("select last_insert_rowid() from athletes")
        athlete_id = cursor.fetchone()[0]
        for timing_data in plays[athlete_name].clean_data:
            cursor.execute("INSERT INTO timing_data (athlete_id, value) VALUES (? ,?)", (athlete_id, timing_data))

    cursor.execute("SELECT * FROM athletes WHERE name = ?", ("James Lee",))
    print(cursor.fetchall(), file=sys.stderr)
    cursor.execute("SELECT * FROM timing_data WHERE athlete_id = ?", (1,))
    print(cursor.fetchall(), file=sys.stderr)
    connection.commit()
    connection.close()

connection = sqlite3.connect("target/coach_data.sqlite")
cursor = connection.cursor()

cursor.execute("SELECT name FROM athletes")
play_names = [row[0] for row in cursor.fetchall()]

print(yate.start_response())
print(yate.include_header("Coack Kelly's List of Plays, attention: data comes from sqlite3"))
print(yate.start_form("add_timing_data.py"))
print(yate.para("Click here to select an play you want to add timing data"))
for play_name in play_names:
    print(yate.radio_button("which_player", play_name))
print("Entering a timing value:")
print(yate.text_input("time_value"))
print(yate.end_form("Select to add or show detail"))
print(yate.include_footer({"Click here to go Home": "/index.html"}))

connection.commit()
connection.close()
