import json

import athletemodel
import yate

print(yate.start_response("application/json"))
print(json.dumps(sorted(athletemodel.get_name_from_store())))
