
    const article_container_height = $(".article-container").outerHeight();


$(".side-option-box").ready(()=>{
    $(".side-option-box").css("height", article_container_height +"px");

    if(isLiked){
        love_icon.attr("src", "/img/heart_liked.png");
        likes_btn.addClass("btn-active")
    }
})



const likes_btn = $("#likes-btn");

likes_btn.on('click', ()=>{
    location.href = $(location).attr('href') + "/likes?articleId=" + articleId;
})

const love_icon = $(".love-icon");

likes_btn.hover(
()=>{
    if(love_icon.attr("src") == "/img/heart_unliked.png"){
        love_icon.attr("src", "/img/heart_unliked_hover.png");
    }
},
()=>{
    if(love_icon.attr("src") == "/img/heart_unliked_hover.png"){
        love_icon.attr("src", "/img/heart_unliked.png");
    }
}
)




const share_btn = $("#share-btn");

share_btn.on('click', ()=>{
    location.href = $(location).attr('href') + "/likes?articleId=" + articleId;
})

const share_icon = $(".share-icon");

share_btn.hover(
()=>{
    share_icon.attr("src", "/img/share_hover.png");
},
()=>{
    share_icon.attr("src", "/img/share.png");
}
)

