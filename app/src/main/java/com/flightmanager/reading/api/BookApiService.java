package com.flightmanager.reading.api;


import com.flightmanager.reading.bean.AutoComplete;
import com.flightmanager.reading.bean.BookDetail;
import com.flightmanager.reading.bean.BookHelp;
import com.flightmanager.reading.bean.BookHelpList;
import com.flightmanager.reading.bean.BookListDetail;
import com.flightmanager.reading.bean.BookListTags;
import com.flightmanager.reading.bean.BookLists;
import com.flightmanager.reading.bean.BookMixAToc;
import com.flightmanager.reading.bean.BookRead;
import com.flightmanager.reading.bean.BookReview;
import com.flightmanager.reading.bean.BookReviewList;
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
import com.flightmanager.reading.bean.PostCount;
import com.flightmanager.reading.bean.RankingList;
import com.flightmanager.reading.bean.Rankings;
import com.flightmanager.reading.bean.Recommend;
import com.flightmanager.reading.bean.RecommendBookList;
import com.flightmanager.reading.bean.SearchDetail;
import com.flightmanager.reading.bean.user.Following;
import com.flightmanager.reading.bean.user.Login;
import com.flightmanager.reading.bean.user.LoginReq;

import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface BookApiService {

    @GET("/book/recommend")
    Observable<Recommend>getRecommend(@Query("gender") String gender);


    /**
     * 获取正版源（若有）与盗版源
     * @param view
     * @param book
     * @return
     */
    @GET("/atoc")
    Observable<List<BookSource>>getABookSource(@Query("view") String view,@Query("book") String book);

    /**
     * 只能获取正版源
     * @param book
     * @param view
     * @return
     */
    @GET("/btoc")
    Observable<List<BookSource>>getBBookSource(@Path("view") String view,@Query("book") String book);

    @GET("/mix-atoc/{bookId}")
    Observable<BookMixAToc>getBookMixAToc(@Path("bookId") String bookId,@Query("view") String view );

    @GET("/mix-toc/{bookId}")
    Observable<BookRead>getBookRead(@Path("bookId") String bookId);

    @GET("/btoc/{bookId}")
    Observable<BookMixAToc>getBookBToc(@Path("bookId") String bookId,@Query("view") String view);

    @GET("http://chapter2.zhuishushenqi.com/chapter/{url}")
    Observable<ChapterRead>getChapterRead(@Path("url") String url);

    @GET("/post/post-count-by-book")
    Observable<PostCount>postCountByBook(@Query("bookId") String bookId);

    @GET("/post/total-count")
    Observable<PostCount>postTotalCount(@Query("books") String bookId);

    @GET("/book/hot-word")
    Observable<HotWord>getHotWord();

    /**
     * 关键字自动补全
     * @param query
     * @return
     */
    @GET("/book/auto-complete")
    Observable<AutoComplete>autoComplete(@Query("query") String query);

    /**
     *书籍查询
     * @param query
     * @return
     */
    @GET("/book/fuzzy-search")
    Observable<SearchDetail> searchBooks(@Query("query") String query);

    /**
     * 通过作者查询书名
     * @param author
     * @return
     */
    @GET("/book/accurate-search")
    Observable<BooksByTag> searchBooksAuthor(@Query("author") String author);

    /**
     * 热门评论
     * @param book
     * @return
     */
    @GET("/post/review/best-by-book")
    Observable<HotReview> getHotReview(@Query("book") String book);

    @GET("/book-list/{bookId}/recommend")
    Observable<RecommendBookList>getRecommendBookList(@Path("bookId")String bookId, @Query("limit") String limit);

    @GET("/book/{bookId}")
    Observable<BookDetail>getBookDetail(@Path("bookId") String bookId);

    @GET("/book/by-tags")
    Observable<BooksByTag>getBooksByTag(@Query("tags") String tags,@Query("start") String start,@Query("limit") String limit);

    /**
     * 获取所有排行榜
     * @return
     */
    @GET("/ranking/gender")
    Observable<RankingList>getRanking();

    /**
     * 获取单一排行榜
     * 周榜：rankingId->_id
     * 月榜：rankingId->monthRank
     * 总榜:rankingId->totalRank
     * @param rankingId
     * @return
     */
    @GET("/ranking/{rankingId}")
    Observable<Rankings>getRankings(@Path("rankingId") String rankingId);

    /**
     * 获取书单列表
     * 本周最热：duration=last-seven-day&sort=collectorCount
     * 最新发布：duration=all&sort=created
     * 最多收藏：duration=all&sort=collecttorCount
     * @param duration
     * @param sort
     * @param start
     * @param limit     20
     * @param tag       都市、古代、架空、重生、玄幻、网游
     * @param gender    male、female
     * @return
     */
    @GET("/book-list")
    Observable<BookLists>getBookLists(@Query("duration") String duration,@Query("sort") String sort,@Query("start") String start,@Query("limit") String limit,@Query("tag") String tag,@Query("gender") String gender);

    /**
     * 获取主题书单标签列表
     * @return
     */
    @GET("/book-list/tagType")
    Observable<BookListTags>getBookListTas();

    /**
     * 获取书单详情
     * @param bookListId
     * @return
     */
    @GET("/bool-list/{bookListId}")
    Observable<BookListDetail>getBookListDetail(@Path("bookListId") String bookListId);

    /**
     * 获取分类
     * @return
     */
    @GET("/cats/lv2/statistics")
    Observable<CategoryList>getCategoryList();


    /**
     * 获取二级分类
     * @return
     */
    @GET("/cats/lv2")
    Observable<CategoryListLv2>getCategoryListv2();

    /**
     * 按分类获取书籍列表
     * @param gender    male、femal
     * @param type      hot(热门)、new(新书)、reputation(好评)、over(完结)
     * @param major     玄幻
     * @param minor     东方玄幻、异界大陆、异界争霸、远古神话
     * @param start
     * @param limit     50
     * @return
     */
    @GET("/book/by-categories")
    Observable<BooksByCats>getBooksByCats(@Query("gender") String gender,@Query("type") String type,@Query("major") String major,@Query("minor") String minor,@Query("start") int start,@Query("limit") int limit);


    /**
     *   * 获取综合讨论区帖子列表
     * 全部、默认排序  http://api.zhuishushenqi.com/post/by-block?block=ramble&duration=all&sort=updated&type=all&start=0&limit=20&distillate=
     * 精品、默认排序  http://api.zhuishushenqi.com/post/by-block?block=ramble&duration=all&sort=updated&type=all&start=0&limit=20&distillate=true
     *
     * @param block      ramble:综合讨论区
     *                   original：原创区
     * @param duration   all
     * @param sort       updated(默认排序)
     *                   created(最新发布)
     *                   comment-count(最多评论)
     * @param type       all
     * @param start      0
     * @param limit      20
     * @param distillate true(精品)
     * @return
     */
    @GET("/post/by-block")
    Observable<DisscussionList>getBookDisscussionList(@Query("block") String block,@Query("duration") String duration,@Query("sort") String sort,@Query("type") String type,@Query("start") String start,@Query("limit") String limit,@Query("distillate") String distillate);



    /**
     * 获取综合讨论区帖子详情
     * @param disscussionId
     * @return
     */
    @GET("/post/{disscussionId}")
    Observable<Disscussion>getBookDisscussionDetail(@Path("disscussionId") String disscussionId);



    /**
     * 获取神评论列表（综合讨论区、书评区、书荒区皆为同一接口）
     * @param disscussionId -> _id
     * @return
     */
    @GET("/post/{disscussionId}/comment/best")
    Observable<CommentList>getBestComments(@Path("disscussionId") String disscussionId);

    /**
     * 获取综合讨论区帖子详情内的讨论列表
     * @param disscussionId
     * @param start             0
     * @param limit             30
     * @return
     */
    @GET("/post/{disscussionId}/comment")
    Observable<CommentList> getBookDisscussionComments(@Path("disscussionId") String disscussionId,@Query("start") String start, @Query("limit") String limit);


    /**
     * 获取书评区帖子列表
     * 全部、全部类型、默认排序  http://api.zhuishushenqi.com/post/review?duration=all&sort=updated&type=all&start=0&limit=20&distillate=
     * 精品、玄幻奇幻、默认排序  http://api.zhuishushenqi.com/post/review?duration=all&sort=updated&type=xhqh&start=0&limit=20&distillate=true
     * @param duration      all
     * @param sort          updated(默认排序)
     *                      created(最新发布)
     *                      comment-count(最多评论)
     * @param type
     * @param start         0
     * @param limit         20
     * @param distillate    true(精品)、空字符(全部)
     * @return
     */
    @GET("/post/review")
    Observable<BookReviewList>getBookReviewList(@Query("duration") String duration,@Query("sort") String sort,@Query("type") String type,@Query("start") String start,@Query("limit") String limit,@Query("distillate") String distillate);


    /**
     * 获取书评区帖子详情
     * @param bookReviewId
     * @return
     */
    @GET("/post/review/{bookReviewId}")
    Observable<BookReview>getBookReviewDetail(@Path("bookReviewId") String bookReviewId);


    /**
     * 获取书评区、书荒区帖子详情内的评论列表
     * @param bookReviewId
     * @param start
     * @param limit
     * @return
     */
    @GET("/post/review/{bookReviewId}/comment")
    Observable<CommentList>getBookReviewComments(@Path("bookReviewId") String bookReviewId,@Query("start") String start, @Query("limit") String limit);


    /**
     * 获取书荒区帖子列表
     * 全部、默认排序  http://api.zhuishushenqi.com/post/help?duration=all&sort=updated&start=0&limit=20&distillate=
     * 精品、默认排序  http://api.zhuishushenqi.com/post/help?duration=all&sort=updated&start=0&limit=20&distillate=true
     *
     * @param duration      all
     * @param sort          updated(默认排序)
     *                      created(最新发布)
     *                      comment-count(最多讨论)
     * @param start         0
     * @param limit         20
     * @param disttilate    true(精品)、空字符（全部）
     * @return
     */
    @GET("post/help")
    Observable<BookHelpList>getBookHelpList(@Query("duration") String duration,@Query("sort") String sort,@Query("start") String start,@Query("limit") String limit,@Query("disttilate") String disttilate);


    /**
     * 获取书荒区帖子详情
     * @param helpId
     * @return
     */
    @GET("post/help/{helpId}")
    Observable<BookHelp>getBookHelpDetail(@Path("helpId") String helpId);

    /**
     * 第三方登录
     * @param platform_uid
     * @param platform_token
     * @param platform_code  “QQ”
     * @param loginReq
     * @return
     */
    @POST("/user/login")
    Observable<Login>login(@Body LoginReq loginReq);

    @GET("/user/followings/{userid}")
    Observable<Following>getFollowings(@Path("userid") String userId);

    /**
     *
     * 获取书籍详情讨论列表
     *
     * @param book  bookId
     * @param sort  updated(默认排序)
     *              created(最新发布)
     *              comment-count(最多评论)
     * @param type  normal
     *              vote
     * @param start 0
     * @param limit 20
     * @return
     */
    @GET("/post/by-book")
    Observable<DisscussionList>getBookDetailDisscussionList(@Query("book") String book,@Query("sort") String sort,@Query("type") String type,@Query("start") String start,@Query("limit") String limit);

    /**
     * 获取书籍详情书评列表
     *
     * @param book  bookId
     * @param sort  updated(默认排序)
     *              created(最新发布)
     *              helpful(最有用的)
     *              comment-count(最多评论)
     * @param start 0
     * @param limit 20
     * @return
     */
    @GET("/post/review/by-book")
    Observable<HotReview>getBookDetailReviewList(@Query("book") String book,@Query("sort") String sort,@Query("start") String start,@Query("limit") String limit);

    @GET("/post/original")
    Observable<DisscussionList>getBookOriginalList(@Query("block") String block,@Query("duration") String duration,@Query("sort") String sort,@Query("type") String type,@Query("start") String start,@Query("limit") String limit,@Query("distillate") String distillate);























}
