
    const article_container_height = $(".article-container").outerHeight();


$(".side-option-box").ready(()=>{
    $(".side-option-box").css("height", article_container_height +"px");
})



const likes_btn = $("#likes-btn");

likes_btn.on('click', ()=>{
    location.href = $(location).attr('href') + "/likes?articleId=" + articleId;
})




