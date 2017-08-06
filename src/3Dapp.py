import os
from flask import Flask, request


app = Flask(__name__)

@app.route("/", methods=['POST', 'GET'])
def index():
    command = request.args.get('command', '')
    name = request.args.get('name', 'person')
    location = request.args.get('location', '')
    filename = '{0}.scad'.format(name)
    filepath = '/home/fab/FF/3DP/HackSociety/OpenScad/{0}'.format(filename)
    replacement = "sed 's/Ubicacion_Fractura = 20/Ubicacion_Fractura = {0}/g' /home/fab/FF/3DP/HackSociety/OpenScad/Humero_V1.scad".format(location)
    if command == 'print':
        with open('/home/fab/FF/3DP/HackSociety/OpenScad/Humero_V1.scad') as fin, open(filepath, 'w') as fout:
            content = str(os.popen(replacement).read())
            fout.write(content)
        os.system('openscad -o /home/fab/FF/3DP/HackSociety/OpenScad/{0}.stl /home/fab/FF/3DP/HackSociety/OpenScad/{1}'.format(name, filename))
        os.system('/home/fab/FF/3DP/HackSociety/SLIC3R_V2.9/Slic3r/bin/slic3r /home/fab/FF/3DP/HackSociety/OpenScad/{0}.stl --load /home/fab/FF/3DP/HackSociety/K_i3_V0.ini --layer-height 0.25'.format(name))
        msg = 'Printed'
    else:
        msg = 'Welcome to Bones3D'

    return msg
