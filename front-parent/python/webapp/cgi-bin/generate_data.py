import cgi
import json
import sys

import athletemodel
import yate

athletes = athletemodel.get_from_store()
form_data = cgi.FieldStorage()
athlete_name = form_data['which_athlete'].value
print(yate.start_response("application/json"))
"""输出内容到控制台以方便调试"""
print(json.dumps(athletes[athlete_name]), file=sys.stderr)
print(json.dumps(athletes[athlete_name].as_dict))
