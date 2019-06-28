package by.itacademy.myapp.util

import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation as CropCircleTransformation

fun picassoLoader(uri:String, imageView: ImageView){
    Picasso.get()
        .load(uri)
        .into(imageView)
}
fun picassoLoader(uri:String, adressImageForError: Drawable, imageView: ImageView){
    Picasso.get()
        .load(uri)
        .error(adressImageForError)
        .into(imageView)
}

fun picassoLoaderCircle(uri: String, imageView: ImageView){
    Picasso.get()
        .load(uri)
        .transform(CropCircleTransformation())
        .into(imageView)
}

