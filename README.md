### EraseBG is an Open Source Native Android App powered by On Device Machine Learning to remove background of images.

### It uses Subject Segmentation provided by the ML Kit for Android to extract Foreground Subject from an Image and gives output in the form of Foreground Confidence Mask and/or Foreground Bitmap.

It requires internet once just after installation for downloading the Subject Segmentation Module which is currently being offered in beta.

Sdk name: play-services-mlkit-subject-segmentation
Implementation: Unbundled, the model is dynamically added using Google Play Services
App size impact: ~200KB size increase

### Getting Started:

1. Clone the project repository:

```bash
git clone https://github.com/Harsh-apk/EraseBG.git
```

2. Open the project in Android Studio IDE.
3. Install the necessary dependencies.
4. Build and run the project.

#Snaps:


| Home Screen                      | Selected Image Screen             | Result Screen                     |
|----------------------------------|-----------------------------------|-----------------------------------|
| ![](/snaps/img_1.png)            | ![](/snaps/img.png)               | ![](/snaps/img_2.png)   |




Why ON DEVICE MACHINE LEARNING ?

On-device machine learning (ML) enables real-time processing, reducing latency and improving user experience. It also enhances privacy and security by processing data locally, without relying on cloud connectivity. Additionally, on-device ML reduces dependence on internet bandwidth, making it ideal for applications that require instant responses, like virtual assistants or augmented reality.

Upcoming Features :
~ Share Image
~ Change Background
~ Upgraded UI flow using jetpack navigation
~ Previous Edits Page
~ Multi Subject Bitmasking and bg removal

### Contributing:

Contributions to the EraseBG App are welcome!

### Contact:

For any questions or inquiries, please contact the project maintainers.


The app is currently in development and new features are being added regularly.

__* Enjoy using the EraseBG App!__
