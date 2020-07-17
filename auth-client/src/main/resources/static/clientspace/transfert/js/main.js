$(document).ready(function(){

  var container = $(".uic-wrapper");
  var nextBtn = $("nav .btn-next");
  var backBtn = $("nav .btn-back");
  var finishBtn = $("nav .btn-finish");

  updateNav();

  function updateNav(){
    var hasAnyRemovedCard = $(".toRight").length ? true : false,
        isCardLast = $(".card-middle").length ? false : true;

    if(hasAnyRemovedCard) {
      backBtn.removeClass('back-btn-hide');

    } else {
      backBtn.addClass('back-btn-hide');
      $(".card-front").addClass('noBack');
    }

    if(isCardLast){
      nextBtn.hide();
      finishBtn.removeClass("hide");
    } else {
      nextBtn.show();
      finishBtn.addClass("hide");
    }
  }

  function showNextCard(){
    //Check if there is only one card left
    if($(".card-middle").length > 0){
      var currentCard = $(".card-front"),
        middleCard = $(".card-middle"),
        backCard = $(".card-back"),
        outCard = $(".card-out").eq(0);

      //Remove the front card
      currentCard.removeClass('card-front').addClass('toRight');
      //change the card places
      middleCard.removeClass('card-middle').addClass('card-front');
      backCard.removeClass('card-back').addClass('card-middle');
      outCard.removeClass('card-out').addClass('card-back');

      updateNav();
    }
  }

  function showPreviousCard(){
    var currentCard = $(".card-front"),
        middleCard = $(".card-middle"),
        backCard = $(".card-back"),
        lastRemovedCard = $(".toRight").slice(-1);

    lastRemovedCard.removeClass('toRight').addClass('card-front');
    currentCard.removeClass('card-front').addClass('card-middle');
    middleCard.removeClass('card-middle').addClass('card-back');
    backCard.removeClass('card-back').addClass('card-out');

    updateNav();
  }

  nextBtn.on('click', function(){
    showNextCard();
  });

  backBtn.on('click', function(){
    showPreviousCard();
  })

});