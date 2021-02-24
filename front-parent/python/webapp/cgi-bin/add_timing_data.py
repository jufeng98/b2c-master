import cgi
import os
import sqlite3
import sys
import time

import athletemodel
import yate

addr = os.environ["REMOTE_ADDR"]
host = os.environ["REMOTE_HOST"]
method = os.environ["REQUEST_METHOD"]

cur_time = time.asctime(time.localtime())

print(addr + " " + host + " " + method + " " + cur_time, file=sys.stderr)

athletes = athletemodel.get_from_store()
form_data = cgi.FieldStorage()
athlete_name = form_data['which_player'].value
if len(form_data.list) == 1:
    connection = sqlite3.connect("target/coach_data.sqlite")
    cursor = connection.cursor()
    cursor.execute("SELECT value FROM timing_data as t INNER JOIN athletes as a on a.id=t.athlete_id where a.name=?",
                   (athlete_name,))
    values = cursor.fetchall()
    times = []
    for value in values:
        times.append(value[0])
    print(yate.start_response())
    print(yate.include_header("Coack Kelly's timing data"))
    print(yate.header("player name:" + athlete_name))
    print(yate.para("top times"))
    print(yate.u_list(times))
    print(yate.include_footer(
        {"Click here to go Home": "/index.html", "Click here to select another player": "generateL_list_sqlite3.py"}
    ))
    connection.commit()
    connection.close()
else:
    time_value = form_data['time_value'].value
    connection = sqlite3.connect("target/coach_data.sqlite")
    cursor = connection.cursor()
    cursor.execute("SELECT id FROM athletes WHERE name = ?", (athlete_name,))
    athlete_id = cursor.fetchone()[0]
    cursor.execute("INSERT INTO timing_data (athlete_id, value) VALUES (? ,?)", (athlete_id, time_value))
    connection.commit()
    connection.close()
    print(yate.start_response())
    print(yate.include_header("OK"))
    print(yate.include_footer({"Click here to go Home": "/index.html"}))
