

def prediction_machine(img_path):
    prediction = np.argmax(model.predict(np.expand_dims(tensor, axis=0)))
    return prediction
