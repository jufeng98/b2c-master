import cgi
import cgitb

import athletemodel
import yate

cgitb.enable()
plays = athletemodel.get_from_store()
form_data = cgi.FieldStorage()
name = form_data['which_player'].value
print(yate.start_response())
print(yate.include_header("Coack Kelly's timing data"))
print(yate.header("player name:" + name + ",birthday:" + plays[name].dob))
print(yate.para("top times"))
print(yate.u_list(plays[name].top3))
print(yate.include_footer(
    {"Click here to go Home": "/index.html", "Click here to select another player": "generate_list.py"}
))
