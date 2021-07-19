package space.quiz.freetoplaygames.UI.adapters

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import space.quiz.freetoplaygames.Models.Screenshot
import space.quiz.freetoplaygames.databinding.ScreenshotItemBinding

class ScreenshotAdapter(private var screenList: List<Screenshot>, private val onClickListener: ScreenOnClickListener,
                        val context: Context): RecyclerView.Adapter<ScreenshotAdapter.ScreenshotHolder>(){

    inner class ScreenshotHolder(val binding: ScreenshotItemBinding): RecyclerView.ViewHolder(binding.root){}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScreenshotHolder {
        val mBinding = ScreenshotItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ScreenshotHolder(mBinding)
    }

    override fun onBindViewHolder(holder: ScreenshotHolder, position: Int) {
        val screen = screenList[position]
        val uri = Uri.parse(screen.image)
        Glide
            .with(context)
            .load(uri)
            .into(holder.binding.sreenItemImage)

        holder.itemView.setOnClickListener {
            onClickListener.onClicked(screen)
        }
    }

    override fun getItemCount(): Int {
        return screenList.size
    }
}