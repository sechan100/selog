const article_container_height = $(".article-container").outerHeight();


$(".side-option-box").ready(()=>{
    $(".side-option-box").css("height", article_container_height +"px");
})