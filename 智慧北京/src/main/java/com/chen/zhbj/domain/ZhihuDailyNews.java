package com.chen.zhbj.domain;

import java.util.List;

/**
 * Created by Lizhaotailang on 2016/8/20.
 */

public class ZhihuDailyNews {

    /**
     * date : 20161201
     * stories : [{"images":["http://pic3.zhimg.com/3655acef2e53f382070290cfd587519e.jpg"],"type":0,"id":9024071,"ga_prefix":"120109","title":"举铁不是年轻人的专利，老人做做更能健康长寿"},{"title":"没有缝隙的铁轨，是这样焊接起来的","ga_prefix":"120108","images":["http://pic4.zhimg.com/0d8b102d2c7b4fb0cb09dc3cc4606613.jpg"],"multipic":true,"type":0,"id":9022146},{"images":["http://pic2.zhimg.com/d965a38afc939750cc5cb8dd1cd5b7fd.jpg"],"type":0,"id":9025654,"ga_prefix":"120107","title":"想要创业或者投资，懂点儿心理学都很有用"},{"images":["http://pic4.zhimg.com/54195cb61d935631249b3541191e0f5f.jpg"],"type":0,"id":9026063,"ga_prefix":"120107","title":"如果你也有一个「自由职业」的梦想"},{"images":["http://pic4.zhimg.com/58d0b5079b4078f22d06babbc459aa13.jpg"],"type":0,"id":9026204,"ga_prefix":"120107","title":"口红的毒性不用小题大做，但敏感人群还是需要注意"},{"images":["http://pic4.zhimg.com/5f259b18266d2e9c0c64a3ea94632473.jpg"],"type":0,"id":9026478,"ga_prefix":"120107","title":"读读日报 24 小时热门 TOP 5 · 「横眉冷对千夫指」"},{"images":["http://pic3.zhimg.com/9c7eebeb525f7f9135fd961080b80a2e.jpg"],"type":0,"id":9024081,"ga_prefix":"120106","title":"瞎扯 · 如何正确地吐槽"}]
     * top_stories : [{"image":"http://pic4.zhimg.com/c72caeafaadb654a19794c04862cca0b.jpg","type":0,"id":9026478,"ga_prefix":"120107","title":"读读日报 24 小时热门 TOP 5 · 「横眉冷对千夫指」"},{"image":"http://pic2.zhimg.com/bb6e7fdb345ef557b1c5ba99a4d4952d.jpg","type":0,"id":9026063,"ga_prefix":"120107","title":"如果你也有一个「自由职业」的梦想"},{"image":"http://pic3.zhimg.com/adf79f43ce96fb392b96a7c912e5836a.jpg","type":0,"id":9025633,"ga_prefix":"113019","title":"刚刚申遗成功的二十四节气，用了几千年，现在还准吗？"},{"image":"http://pic4.zhimg.com/dabc623e425cf49a9a8388568dba9883.jpg","type":0,"id":9025416,"ga_prefix":"113017","title":"知乎好问题 · 如何判断「不想要」是否出自真心？"},{"image":"http://pic1.zhimg.com/6c24d107bb34ba7d2062d879658c5f00.jpg","type":0,"id":9024113,"ga_prefix":"113014","title":"跟孩子这样说话，就不会把好好的天聊死"}]
     */

    private String date;
    private List<StoriesBean> stories;
    private List<TopStoriesBean> top_stories;

    public ZhihuDailyNews() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<StoriesBean> getStories() {
        return stories;
    }

    public void setStories(List<StoriesBean> stories) {
        this.stories = stories;
    }

    public List<TopStoriesBean> getTop_stories() {
        return top_stories;
    }

    public void setTop_stories(List<TopStoriesBean> top_stories) {
        this.top_stories = top_stories;
    }

    public static class StoriesBean {
        /**
         * images : ["http://pic3.zhimg.com/3655acef2e53f382070290cfd587519e.jpg"]
         * type : 0
         * id : 9024071
         * ga_prefix : 120109
         * title : 举铁不是年轻人的专利，老人做做更能健康长寿
         * multipic : true
         */

        private int type;
        private int id;
        private String ga_prefix;
        private String title;
        private boolean multipic;
        private List<String> images;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public boolean isMultipic() {
            return multipic;
        }

        public void setMultipic(boolean multipic) {
            this.multipic = multipic;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }

        @Override
        public String toString() {
            return "StoriesBean{" +
                    "id=" + id +
                    ", title='" + title + '\'' +
                    '}';
        }
    }

    public static class TopStoriesBean {
        /**
         * image : http://pic4.zhimg.com/c72caeafaadb654a19794c04862cca0b.jpg
         * type : 0
         * id : 9026478
         * ga_prefix : 120107
         * title : 读读日报 24 小时热门 TOP 5 · 「横眉冷对千夫指」
         */

        private String image;
        private int type;
        private int id;
        private String ga_prefix;
        private String title;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        @Override
        public String toString() {
            return "TopStoriesBean{" +
                    "id=" + id +
                    ", title='" + title + '\'' +
                    '}';
        }
    }


}
