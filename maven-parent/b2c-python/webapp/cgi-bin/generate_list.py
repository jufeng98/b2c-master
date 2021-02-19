import glob

import athletemodel
import yate

files = glob.glob("data/*.txt")
plays = athletemodel.put_to_store(files)
print(yate.start_response())
print(yate.include_header("Coack Kelly's List of Plays, attention: data comes from pickle"))
print(yate.start_form("generate_time_data.py"))
print(yate.para("Click here to select an play you want to see"))
for play in plays:
    print(yate.radio_button("which_player", plays[play].name))
print(yate.end_form("Select to show detail"))
print(yate.include_footer({"Click here to go Home": "/index.html"}))
