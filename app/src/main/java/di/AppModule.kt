package di

import com.ldl.ireader.data.http.Apis
import com.ldl.ireader.data.http.ApiService
import com.ldl.ireader.repository.BookDetailRepository
import com.ldl.ireader.repository.DiscoveryRepository
import com.ldl.ireader.viewmodel.BookDetailViewModel
import com.ldl.ireader.viewmodel.DiscoveryViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * 作者：LDL 创建时间：2020/9/8
 * 类说明：
 */
val viewModelModule = module {
    viewModel { DiscoveryViewModel(get()) }
    viewModel { BookDetailViewModel(get()) }
}

val repositoryModule = module {
    factory { DiscoveryRepository(get()) }
    factory { BookDetailRepository(get()) }
}

val remoteModule = module {
    //single 单列注入
    single<Apis> { ApiService }
}

val appModule = listOf(viewModelModule, repositoryModule, remoteModule)