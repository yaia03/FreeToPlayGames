package space.quiz.freetoplaygames.UI.adapters

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import space.quiz.freetoplaygames.Models.Screenshot
import space.quiz.freetoplaygames.databinding.ScreenPageItemBinding
import space.quiz.freetoplaygames.databinding.ScreenshotItemBinding

class ScreenPageAdapter(private var screenList: List<String>, val context: Context) :
    RecyclerView.Adapter<ScreenPageAdapter.ScreenshotHolder>(){

    inner class ScreenshotHolder(val binding: ScreenPageItemBinding): RecyclerView.ViewHolder(binding.root){}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScreenshotHolder {
        val mBinding = ScreenPageItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ScreenshotHolder(mBinding)
    }

    override fun onBindViewHolder(holder: ScreenshotHolder, position: Int) {
        val screen = screenList[position]
        val uri = Uri.parse(screen)
        Glide
            .with(context)
            .load(uri)
            .into(holder.binding.screenPageItemImage)
    }

    override fun getItemCount(): Int {
        return screenList.size
    }
}