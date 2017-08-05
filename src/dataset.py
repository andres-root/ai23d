from glob import glob
from keras.utils import np_utils
from sklearn.datasets import load_files
import numpy as np


def load_dataset(path):
    data = load_files(path)
    dog_files = np.array(data['filenames'])
    dog_targets = np_utils.to_categorical(np.array(data['target']), 133)
    return dog_files, dog_targets


names = [item[20:-1] for item in sorted(glob("images/temporal/train/*/"))]
train_files, train_targets = load_dataset('images/temporal/train')
valid_files, valid_targets = load_dataset('images/temporal/valid')
test_files, test_targets = load_dataset('images/temporal/test')
