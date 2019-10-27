package alexsullivan.com.reactivetodo

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

inline fun <reified T : ViewModel> AppCompatActivity.buildViewModel(crossinline viewModelFactory: () -> T): T {
  return ViewModelProviders.of(this, object : ViewModelProvider.NewInstanceFactory() {
    override fun <A : ViewModel?> create(modelClass: Class<A>): A {
      return viewModelFactory() as A
    }
  }).get(T::class.java)
}

inline fun <reified T : ViewModel> Fragment.buildViewModel(crossinline viewModelFactory: () -> T): T {
  return ViewModelProviders.of(this, object : ViewModelProvider.NewInstanceFactory() {
    override fun <A : ViewModel?> create(modelClass: Class<A>): A {
      return viewModelFactory() as A
    }
  }).get(T::class.java)
}
