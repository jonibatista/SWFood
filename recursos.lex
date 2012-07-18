%{
#include <stdio.h>
#include "y.tab.h"
%}
%%
[ \t\(\)'&]+ {;}

#CLASSES
vinho|cerveja|aguardente|brandy|gin|rum|whisky|licor|espumante|sumo|agua|nectar|cafe|cha {yylval=strdup(yytext);return(CLASSE_BEBIDA);}
leit[es]|nat[as] {yylval=strdup(yytext);return(CLASSE_LATICINIO);}

#PROPRIEDADES
aromas?|reserva|achocolatado|aromatizado|mineral|natural|infusao|soluvel {yylval=strdup(yytext);return(PROPRIEDADE_GERAL);}
[12][90][0-9]{2} {yylval=strdup(yytext);return(PROPRIEDADE_ANO);}
lt?|l|cl|ml|litro|% {yylval=strdup(yytext);return(PROPRIEDADE_GRANDEZA);}
branc[ao]|tinto|pret[ao]|verdes?|vermelh[ao]s?|rose|palhete {yylval=strdup(yytext);return(PROPRIEDADE_COR);}
alcoolica[s]|refrigerante {yylval=strdup(yytext);return(PROPRIEDADE_TIPOBEBIDA);}
[0-9]+\.[0-9]+ {yylval=strdup(yytext);return(ALGEBRICO_DECIMAL);}
[0-9]+ {yylval=strdup(yytext);return(ALGEBRICO_INTEIRO);}
cabra|ovelha|vaca|porto|madeira {yylval=strdup(yytext);return(PROPRIEDADE_ORIGEM);}
pasteurizad[ao]|esterilizad[ao]|cru|condensado|uht|po|evaporado|destilada[s]|fermentada[s] {yylval=strdup(yytext);return(PROPRIEDADE_TIPOPROCESSAMENTO);}
gaseificad[ao]|gasocarbonica|extra|bruto|doce|seco|maduro|sem|simple[s]|alcool {yylval=strdup(yytext);return(PROPRIEDADE_CLASSIFICACAO);}
gordo|magro|meio|light {yylval=strdup(yytext);return(PROPRIEDADE_CALORICA);}
laranja|morango|tutti?frutti|tutti|frutti|tropical|manga|ananas|limao|pessego|ervas|maca|tomate|pera|alperce|frutos|ginja {yylval=strdup(yytext);return(PROPRIEDADE_SABOR);}
borba|casa|esteva|marques|montado|monte|velho|olho|mocho|pias|planalto|quinta|aveledada|carmo|portal|rocim|tapada|herd[.a-z]|coe? {yylval=strdup(yytext);return(PROPRIEDADE_MARCA);}


#OUTRAS	
[a-zA-Z]+ {yylval=strdup(yytext);return(PALAVRA_DESCONHECIDA);}
%%
