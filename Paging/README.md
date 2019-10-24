# Paging


### Demo

The app is built by following [Android Paging codelab](https://codelabs.developers.google.com/codelabs/android-paging/index.html?index=..%2F..%2Findex#0).

The following are the packages:   

- **api** - contains Github API calls, using Retrofit
- **db** - database cache for network data
- **data** - contains the repository class, responsible for triggering API requests and saving the response in the database
- **ui** - contains classes related to displaying an Activity with a RecyclerView
- **model** - contains the Repo data model, which is also a table in the Room database; and RepoSearchResult, a class that is used by the UI to observe both search results data and network errors

### Reference


- [Android Developer Docs - Paging](https://developer.android.com/topic/libraries/architecture/paging)
- [Android Paging codelab](https://codelabs.developers.google.com/codelabs/android-paging/index.html?index=..%2F..%2Findex#0)
