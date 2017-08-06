import matplotlib.pyplot as plt
import cv2

final_images = np.array(glob('images/final/*'))

for img_path in final_images:
    p_img = cv2.imread(img_path)
    plt.imshow(p_img)
    plt.show()
    prediction = prediction_machine(img_path)
    print('Predicted Disease: {0}'.format(prediction))
