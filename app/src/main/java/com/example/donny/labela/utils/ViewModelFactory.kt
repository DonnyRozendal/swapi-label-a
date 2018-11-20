package com.example.donny.labela.utils

//class ViewModelFactory(private val repository: BaseRepository) : ViewModelProvider.Factory {
//
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(CharacterListViewModel::class.java)) {
//            return CharacterListViewModel(repository as CharacterListRepository) as T
//        }
//        if (modelClass.isAssignableFrom(CharacterDetailViewModel::class.java)) {
//            return CharacterDetailViewModel(repository as CharacterDetailRepository) as T
//        }
//        throw IllegalArgumentException("Unknown ViewModel class")
//    }
//}