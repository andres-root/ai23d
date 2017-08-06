import os
from flask import Flask, request, redirect, url_for
from werkzeug.utils import secure_filename
import requests

# AI Imports
from keras.preprocessing import image
from tqdm import tqdm
from keras.models import load_model
import numpy as np


# Loading Tensors
def path_to_tensor(img_path):
    img = image.load_img(img_path, target_size=(224, 224))
    x = image.img_to_array(img)
    return np.expand_dims(x, axis=0)


def paths_to_tensor(img_paths):
    list_of_tensors = [path_to_tensor(img_path) for img_path in tqdm(img_paths)]
    return np.vstack(list_of_tensors)


# Prediction Machine
categories = {
    0: 'Fracture',
    1: 'Osteomyelitis',
    2: 'Osteochondroma'
}

# Loading CNN Model
model = load_model("../saved_models/history/weights.best.from_scratch_1_21:00_42%.hdf5")


def prediction_machine(img_path):
    tensor = path_to_tensor(img_path)
    prediction_array = model.predict(tensor)
    prediction = np.argmax(prediction_array)
    return categories[prediction]

UPLOAD_FOLDER = '../images/uploads'
ALLOWED_EXTENSIONS = set(['jpg', 'jpeg'])

app = Flask(__name__)
app.config['UPLOAD_FOLDER'] = UPLOAD_FOLDER


def allowed_file(filename):
    return '.' in filename and \
           filename.rsplit('.', 1)[1].lower() in ALLOWED_EXTENSIONS


@app.route('/', methods=['GET', 'POST'])
def upload_file():
    if request.method == 'POST':
        # if 'bone' not in request.files:
            # return 'No file part'
        file = request.files['bone']
        if file.filename == '':
            return 'No selected file'
        if file and allowed_file(file.filename):
            filename = secure_filename(file.filename)
            file.save(os.path.join(app.config['UPLOAD_FOLDER'], filename))

        # Prediction Machine
        img = request.form['image']
        img_path = '../images/uploads/{0}'.format(img)
        prediction = prediction_machine(img_path)
    return prediction


@app.route('/predict', methods=['GET', 'POST'])
def test():
    img = request.args.get('image', '')
    name = request.args.get('name', '')
    img_path = '../images/uploads/{0}'.format(img)
    prediction = prediction_machine(img_path)
    result = requests.get('http://192.168.43.151:5000/?command=print&name={0}&location=25'.format(name))
    print(result.status_code)
    return prediction
