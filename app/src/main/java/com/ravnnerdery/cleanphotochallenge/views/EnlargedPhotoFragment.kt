package com.ravnnerdery.cleanphotochallenge.views

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.ravnnerdery.cleanphotochallenge.adapters.EnlargedPhotoAdapter
import com.ravnnerdery.cleanphotochallenge.databinding.EnlargedPhotoFragmentBinding
import com.ravnnerdery.cleanphotochallenge.viewModels.EnlargedPhotoViewModel
import com.ravnnerdery.domain.models.PhotoInfo
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

@AndroidEntryPoint
class EnlargedPhotoFragment : Fragment() {

    private val enlargedPhotoViewModel: EnlargedPhotoViewModel by viewModels()
    private lateinit var binding: EnlargedPhotoFragmentBinding
    private lateinit var adapter: EnlargedPhotoAdapter
    private lateinit var snapHelper: SnapHelper
    private lateinit var disposable: Disposable

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = EnlargedPhotoFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = EnlargedPhotoFragmentArgs.fromBundle(requireArguments())
        binding.let {
            adapter = EnlargedPhotoAdapter()
            it.enlargedPhotoRecyclerView.adapter = adapter
            snapHelper = LinearSnapHelper()
            snapHelper.attachToRecyclerView(it.enlargedPhotoRecyclerView)
        }

        val allPhotosObserver = object: Observer<List<PhotoInfo>> {
            override fun onSubscribe(d: Disposable) {
                disposable = d
            }

            override fun onNext(t: List<PhotoInfo>) {
                if (enlargedPhotoViewModel.currentPosition == 0) {
                    if (t.isNotEmpty()) {
                        adapter.submitList(t)
                        val argPosition = args.id.toInt() - 1
                        binding.enlargedPhotoRecyclerView.scrollToPosition(argPosition)
                        enlargedPhotoViewModel.currentPosition = argPosition
                    }
                } else {
                    adapter.submitList(t)
                    enlargedPhotoViewModel.currentPosition?.let { it ->
                        binding.enlargedPhotoRecyclerView.scrollToPosition(
                            it
                        )
                    }
                }
            }

            override fun onError(e: Throwable) {
                e.printStackTrace()
            }

            override fun onComplete() {
                Log.v("PLFragObs","onComplete: ${Thread.currentThread().name}" )
            }
        }
        enlargedPhotoViewModel.allPhotosObservable.execute().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(allPhotosObserver)
    }

    override fun onDestroy() {
        binding.enlargedPhotoRecyclerView.layoutManager?.let { it ->
            val snapView: View? = snapHelper.findSnapView(it)
            val snapPosition = snapView?.let { position -> it.getPosition(position) }
            enlargedPhotoViewModel.currentPosition = snapPosition
            disposable.dispose()
        }
        super.onDestroy()
    }

}