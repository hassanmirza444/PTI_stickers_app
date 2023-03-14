# PhotoEditor


A Photo Editor library with simple, easy support for image editing using Paints, Text, Filters, Emoji and Sticker like stories.

## Features

- [**Drawing**](#drawing) on image with option to change its Brush's Color, Size, Opacity, Erasing and basic shapes.
- Apply [**Filter Effect**](#filter-effect) on image using MediaEffect
- Adding/Editing [**Text**](#text) with option to change its Color with Custom Fonts.
- Adding [**Emoji**](#emoji) with Custom Emoji Fonts.
- Adding [**Images/Stickers**](#adding-imagesstickers)
- Pinch to Scale and Rotate views.
- [**Undo and Redo**](#undo-and-redo) for Brush and Views.
- [**Deleting**](#deleting) Views
- [**Saving**](#saving) Photo after editing.


## Benefits
- Hassle free coding
- Increase efficiency
- Easy image editing

## Setting up the View
First we need to add `PhotoEditorView` in our xml layout. We Have used <ja.burhanrashid52.photoeditor.PhotoEditorView>  in our app.



We can set the image programmatically by getting source from `PhotoEditorView` which will return a `ImageView` so that we can load image from resources,file or (Picasso/Glide)
```java
PhotoEditorView mPhotoEditorView = findViewById(R.id.photoEditorView);

mPhotoEditorView.getSource().setImageResource(R.drawable.got);
```

## Building a PhotoEditor
To use the image editing feature we need to build a PhotoEditor which requires a Context and PhotoEditorView which we have to setup in our xml layout


```java
//Use custom font using latest support library
Typeface mTextRobotoTf = ResourcesCompat.getFont(this, R.font.roboto_medium);

//loading font from asset
Typeface mEmojiTypeFace = Typeface.createFromAsset(getAssets(), "emojione-android.ttf");

mPhotoEditor = new PhotoEditor.Builder(this, mPhotoEditorView)
         .setPinchTextScalable(true)
         .setClipSourceImage(true)
         .setDefaultTextTypeface(mTextRobotoTf)
         .setDefaultEmojiTypeface(mEmojiTypeFace)
         .build();
 ```
We can customize the properties in the PhotoEditor as per our requirement

| Property  | Usage |
| ------------- | ------------- |
| `setPinchTextScalable()`  | set false to disable pinch to zoom on text insertion. Default: true. |
| `setClipSourceImage()` | set true to clip the drawing brush to the source image. Default: false. |
| `setDefaultTextTypeface()`  | set default text font to be added on image  |
| `setDefaultEmojiTypeface()`  | set default font specifc to add emojis |

That's it we are done with setting up our library




 
