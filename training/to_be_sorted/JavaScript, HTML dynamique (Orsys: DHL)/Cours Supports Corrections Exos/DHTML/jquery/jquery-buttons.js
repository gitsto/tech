function semantique() {
  $('#contenu .italique').wrap('<i></i>');
  $('#contenu .gras').wrap('<b></b>');
  $('#contenu .souligne').wrap('<u></u>');
  $('#contenu .barre').wrap('<del></del>');
}
function colorer() {
   $('#contenu .rouge').wrap('<span style="color:red"></span>');
   $('#contenu .vert').wrap('<span style="color:green"></span>');
   $('#contenu .orange').wrap('<span style="color:orange"></span>');
   $('#contenu .bleu').wrap('<span style="color:blue"></span>');
}

function mettreTitres() {
  $('#contenu .titre1').wrap('<h1></h1>');
  $('#contenu .titre2').wrap('<h2></h2>');
}

function liensEnBoutons() {
  $('#contenu a').wrap('<button></button>');
}

function dupliquerTexte() {
  $('#contenu').clone().appendTo('#contenu');
}

function regrouperTitres() {
  $('h1').wrapAll('<div></div>');
  $('h2').wrapAll('<div></div>');
}

function regrouperLiens() {
  $('#contenu a').wrapAll('<div></div>');
}

function viderBoutons() {
  $('#contenu button').empty();
}

function enleverLiens() {
  $('#contenu a').remove();
}

function enleverGras() {
  $('#contenu strong,#contenu b').remove();
}

function enleverItalique() { 
  $('#contenu em,#contenu i').remove(); 
}

function enleverDecor() { 
  $('#contenu *:not(html):not(body):not(p):not(button)').remove(); 
}

function voirCode() {
  $('#contenu').text($('p').html());
}
