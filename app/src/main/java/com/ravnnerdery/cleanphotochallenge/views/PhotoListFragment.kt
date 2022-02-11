package com.ravnnerdery.cleanphotochallenge.views

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.ravnnerdery.cleanphotochallenge.viewModels.PhotoListViewModel
import com.ravnnerdery.cleanphotochallenge.adapters.PhotosAdapter
import com.ravnnerdery.cleanphotochallenge.adapters.viewHolders.photoListViewHolder.PhotoClickListener
import com.ravnnerdery.cleanphotochallenge.databinding.PhotoListFragmentBinding
import com.ravnnerdery.domain.models.PhotoInfo
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

@AndroidEntryPoint
class PhotoListFragment : Fragment() {

    private val photoListViewModel: PhotoListViewModel by viewModels()
    private var binding: PhotoListFragmentBinding? = null
    private lateinit var adapter: PhotosAdapter
    private lateinit var snapHelper: SnapHelper
    private lateinit var disposable: Disposable

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = PhotoListFragmentBinding.inflate(inflater, container, false)
        binding?.let {
            adapter =
                PhotosAdapter(PhotoClickListener { id -> photoListViewModel.onPhotoClicked(id) })
            it.photoListRecyclerView.adapter = adapter
            snapHelper = LinearSnapHelper()
            snapHelper.attachToRecyclerView(it.photoListRecyclerView)
            it.photoListSwypeContainer.setOnRefreshListener {
                Toast.makeText(context,"Updating Posts",Toast.LENGTH_SHORT).show()
                photoListViewModel.refreshData()
            }
        }
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val allPhotosObserver = object : Observer<List<PhotoInfo>> {
            override fun onSubscribe(d: Disposable) {
                disposable = d
            }

            override fun onNext(t: List<PhotoInfo>) {
                adapter.submitList(t)
                if (photoListViewModel.currentPosition != 0) {
                    photoListViewModel.currentPosition?.let { it1 ->
                        binding?.photoListRecyclerView?.scrollToPosition(
                            it1
                        )
                    }
                }
                binding?.let{
                    it.photoListSwypeContainer.isRefreshing = false
                    it.photoListRecyclerView.scrollToPosition(1)
                    Toast.makeText(context,"Posts are up to datea",Toast.LENGTH_SHORT).show()
                }
            }

            override fun onError(e: Throwable) {
                e.printStackTrace()
            }

            override fun onComplete() {
                Log.v("PLFragObs", "onComplete: ${Thread.currentThread().name}")
            }

        }
        photoListViewModel.allPhotosObservable.execute().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(allPhotosObserver)

        photoListViewModel.navigateToSnapshot.observe(this, { photo ->
            photo?.let {
                this.findNavController().navigate(
                    PhotoListFragmentDirections.actionPhotoListFragmentToEnlargedPhotoFragment(photo)
                )
                photoListViewModel.onSnapshotNavigated()
            }
        })
    }

    override fun onDestroy() {
        binding?.photoListRecyclerView?.layoutManager?.let {
            val snapView: View? = snapHelper.findSnapView(it)
            val snapPosition = snapView?.let { position -> it.getPosition(position) }
            photoListViewModel.currentPosition = snapPosition
            disposable.dispose()
        }
        super.onDestroy()
    }

}