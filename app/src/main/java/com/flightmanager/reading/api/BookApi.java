package com.flightmanager.reading.api;


import com.flightmanager.reading.base.Constant;
import com.flightmanager.reading.bean.AutoComplete;
import com.flightmanager.reading.bean.BookDetail;
import com.flightmanager.reading.bean.BookListDetail;
import com.flightmanager.reading.bean.BookListTags;
import com.flightmanager.reading.bean.BookLists;
import com.flightmanager.reading.bean.BookMixAToc;
import com.flightmanager.reading.bean.BookReview;
import com.flightmanager.reading.bean.BookSource;
import com.flightmanager.reading.bean.BooksByCats;
import com.flightmanager.reading.bean.BooksByTag;
import com.flightmanager.reading.bean.CategoryList;
import com.flightmanager.reading.bean.CategoryListLv2;
import com.flightmanager.reading.bean.ChapterRead;
import com.flightmanager.reading.bean.CommentList;
import com.flightmanager.reading.bean.Disscussion;
import com.flightmanager.reading.bean.DisscussionList;
import com.flightmanager.reading.bean.HotReview;
import com.flightmanager.reading.bean.HotWord;
import com.flightmanager.reading.bean.RankingList;
import com.flightmanager.reading.bean.Rankings;
import com.flightmanager.reading.bean.Recommend;
import com.flightmanager.reading.bean.RecommendBookList;
import com.flightmanager.reading.bean.SearchDetail;
import com.flightmanager.reading.bean.user.Login;
import com.flightmanager.reading.bean.user.LoginReq;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Query;
import rx.Observable;

public class BookApi {

    public static BookApi instance;

    private BookApiService service;

    public BookApi(OkHttpClient okHttpClient){
        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl(Constant.API_BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) //添加Rx适配器
                .addConverterFactory(GsonConverterFactory.create()) //添加Gson转换器
                .client(okHttpClient)
                .build();
        service=retrofit.create(BookApiService.class);
    }

    public static BookApi getInstance(OkHttpClient okHttpClient){
        if(instance==null)
            instance=new BookApi(okHttpClient);
            return instance;
    }

    public Observable<Recommend>getRecommend(String gender){
        return service.getRecommend(gender);
    }

    public Observable<HotWord>getHotWord(){
        return service.getHotWord();
    }

    public Observable<AutoComplete>getAutoComplete(String query){
        return service.autoComplete(query);
    }

    public Observable<SearchDetail>getSearchResult(String query){
        return service.searchBooks(query);
    }

    public Observable<BooksByTag>searchBooksByAuthor(String author){
        return service.searchBooksAuthor(author);
    }

    public Observable<BookDetail>getBookDetail(String bookId){
        return service.getBookDetail(bookId);
    }

    public Observable<HotReview>getHotReview(String book){
        return service.getHotReview(book);
    }

    public Observable<RecommendBookList>getRecommendBookList(String bookId,String limit){
        return service.getRecommendBookList(bookId, limit);
    }

    public Observable<BooksByTag>getBooksByTag(String tags,String start,String limit){
        return service.getBooksByTag(tags,start,limit);
    }

    public Observable<BookMixAToc>getBookMixAToc(String bookId,String view){
        return service.getBookMixAToc(bookId,view);
    }

    public synchronized Observable<ChapterRead>getChapterRead(String url){
        return service.getChapterRead(url);
    }

    public synchronized Observable<List<BookSource>> getBookSource(String view, String book){
        return service.getABookSource(view,book);
    }

    public Observable<RankingList>getRanking(){
        return service.getRanking();
    }

    public Observable<Rankings>getRanking(String rankingId){
        return service.getRankings(rankingId);
    }

    public Observable<BookLists>getBookLists(String duration,String sort,String start, String limit,String tag,String gender){
        return service.getBookLists(duration,sort,start,limit,tag,gender);
    }

    public Observable<BookListTags>getBookListTags(){
        return service.getBookListTas();
    }

    public  Observable<BookListDetail>getBookListDetail(String bookListId){
        return service.getBookListDetail(bookListId);
    }

    public synchronized Observable<CategoryList> getCategoryList(){
        return service.getCategoryList();
    }

    public Observable<CategoryListLv2>getCategoryListLv2(){
        return service.getCategoryListv2();
    }

    public Observable<BooksByCats>getBooksByCats(String gender, String type, String major, String minor, int start, @Query("limit") int limit){
        return service.getBooksByCats(gender,type,major,minor,start,limit);
    }

    public Observable<DisscussionList>getBookDisscussionList(String block,String duration,String sort,String type,String start,String limit,String distillate){
        return service.getBookDisscussionList(block,duration,sort,type,start,limit,distillate);
    }

    public Observable<Disscussion>getBookDisscussionDetail(String disscussionId){
        return service.getBookDisscussionDetail(disscussionId);
    }

    public Observable<CommentList>getBestComments(String disscussionId){
        return service.getBestComments(disscussionId);
    }

    public Observable<BookReview>getBookReviewDetail(String bookReviewId){
        return service.getBookReviewDetail(bookReviewId);
    }

    public Observable<Login>login(String platform_uid,String platform_token,String platform_code){
        LoginReq loginReq=new LoginReq();
        loginReq.platform_code=platform_code;
        loginReq.platform_token=platform_token;
        loginReq.platform_uid= platform_uid;
        return service.login(loginReq);
    }

    public Observable<DisscussionList>getBookDetailDisscussionList(String book,String sort,String type,String start,String limit){
        return service.getBookDetailDisscussionList(book,sort,type,start,limit);
    }

    public Observable<HotReview>getBookDetailReviewList(String book,String sort,String start,String limit){
        return service.getBookDetailReviewList(book,sort,start,limit);
    }

    public Observable<DisscussionList>getGirlBookDisscussionList(String block,String duration,String sort,String type,String start,String limit,String distillate){
        return service.getBookDisscussionList(block,duration,sort,type,start,limit,distillate);
    }




}
