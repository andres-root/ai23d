import os

with open('Humero_V1.scad') as fin, open('cosito.scad', 'w') as fout:
    content = str(os.popen("sed 's/Ubicacion_Fractura = */Ubicacion_Fractura = 22222/g' Humero_V1.scad").read())
    print(content)
    fout.write(content)
