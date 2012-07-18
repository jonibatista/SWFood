%{
#include <stdio.h>
#include <string.h>
char buffer[30][1024];int indice=0;char tmp1[1024];
void yyerror(const char *str){fprintf(stderr,"\n\n *** Sintaxe não reconhecida !!! *** %s\n\n",str);} int yywrap() {return 1;} 
main() {yyparse();} 
%}
%token ALGEBRICO_INTEIRO ALGEBRICO_DECIMAL PROPRIEDADE_GRANDEZA PROPRIEDADE_ANO PROPRIEDADE_COR PROPRIEDADE_GERAL CLASSE_BEBIDA PALAVRA_DESCONHECIDA PROPRIEDADE_ORIGEM PROPRIEDADE_TIPOPROCESSAMENTO CLASSE_LATICINIO PROPRIEDADE_CLASSIFICACAO PROPRIEDADE_TIPOBEBIDA PROPRIEDADE_CALORICA PROPRIEDADE_SABOR PROPRIEDADE_MARCA
%%
Descricao : Descricao_Simples {printf("<linha>\n%s\n</linha>\n", $$);}
;
;
Descricao_Simples : Produto Propriedades {sprintf(buffer[indice],"\t%s \n\t%s",$1,$2);$$ = buffer[indice];indice++;}
	| Propriedades Produto {sprintf(buffer[indice],"\t%s \n\t%s",$1,$2);$$ = buffer[indice];indice++;}
	| Propriedades Produto Propriedades {sprintf(buffer[indice],"\t%s \n\t%s \n\t%s",$1,$2,$3);$$ = buffer[indice];indice++;}
	| Produto Propriedades Quantidades {sprintf(buffer[indice],"\t%s \n\t%s \n\t%s",$1,$2, $3);$$ = buffer[indice];indice++;}
	| Produto Propriedades Quantidades Propriedades {sprintf(buffer[indice],"\t%s \n\t%s \n\t%s \n\t%s",$1,$2, $3, $4);$$ = buffer[indice];indice++;}
;

Quantidades	: Sequencia_Quantidades
		| Sequencia_Quantidades Grandeza {sprintf(buffer[indice],"%s \n\t%s",$1,$2);$$ = buffer[indice];indice++;}
		| Sequencia_Quantidades Grandeza Quantidade {sprintf(buffer[indice],"\t%s \n\t%s \n\t%s",$1,$2,$3);$$ = buffer[indice];indice++;}
;
Sequencia_Quantidades	: Quantidade {sprintf(buffer[indice],"%s",$1);$$ = buffer[indice];indice++;}
			| Quantidades Quantidade {sprintf(buffer[indice],"%s \n\t%s",$1,$2);$$ = buffer[indice];indice++;}
;
Quantidade : Numero {sprintf(buffer[indice],"<propriedade atributo=\"quantidade\">%s</propriedade>",$1);$$ = buffer[indice];indice++;}
;
Numero 	: ALGEBRICO_INTEIRO
	| ALGEBRICO_DECIMAL
;
Grandeza : PROPRIEDADE_GRANDEZA {sprintf(buffer[indice],"<propriedade atributo=\"grandeza\">%s</propriedade>",$1);$$ = buffer[indice];indice++;}
;

Propriedades	: Sequencia_Propriedades
;
Sequencia_Propriedades	: Propriedade {sprintf(buffer[indice],"%s",$1);$$ = buffer[indice];indice++;}
			| Propriedades Propriedade {sprintf(buffer[indice],"%s \n\t%s",$1,$2);$$ = buffer[indice];indice++;}
;

Propriedade 	: Palavras_gerais {sprintf(buffer[indice],"<propriedade atributo=\"geral\">%s</propriedade>",$1);$$ = buffer[indice];indice++;}
		| Palavras_cores {sprintf(buffer[indice],"<propriedade atributo=\"cor\">%s</propriedade>",$1);$$ = buffer[indice];indice++;}
		| PROPRIEDADE_ANO {sprintf(buffer[indice],"<propriedade atributo=\"ano\">%s</propriedade>",$1);$$ = buffer[indice];indice++;}
		| PROPRIEDADE_ORIGEM {sprintf(buffer[indice],"<propriedade atributo=\"origem\">%s</propriedade>",$1);$$ = buffer[indice];indice++;}
		| PROPRIEDADE_TIPOPROCESSAMENTO {sprintf(buffer[indice],"<propriedade atributo=\"tipoprocessamento\">%s</propriedade>",$1);$$ = buffer[indice];indice++;}
		| Palavras_sabores {sprintf(buffer[indice],"<propriedade atributo=\"sabor\">%s</propriedade>",$1);$$ = buffer[indice];indice++;}
		| Palavras_classificacao {sprintf(buffer[indice],"<propriedade atributo=\"classificacao\">%s</propriedade>",$1);$$ = buffer[indice];indice++;}
		| Palavras_calorias {sprintf(buffer[indice],"<propriedade atributo=\"calorica\">%s</propriedade>",$1);$$ = buffer[indice];indice++;}
		| Palavras_marcas {sprintf(buffer[indice],"<propriedade atributo=\"marca\">%s</propriedade>",$1);$$ = buffer[indice];indice++;}
		| PROPRIEDADE_TIPOBEBIDA {sprintf(buffer[indice],"<propriedade atributo=\"tipobebida\">%s</propriedade>",$1);$$ = buffer[indice];indice++;}
		| Palavras_Desconhecidas {sprintf(buffer[indice],"<desconhecido>%s</desconhecido>",$1);$$ = buffer[indice];indice++;}
;

Produto		: Palavras_bebidas {sprintf(buffer[indice],"<produto familia=\"bebida\">%s</produto>",$1);$$ = buffer[indice];indice++;sprintf(tmp1,"\"%s\" familia=\"classe_bebida\"",$1);}
		| CLASSE_LATICINIO {sprintf(buffer[indice],"<produto familia=\"laticinio\">%s</produto>",$1);$$ = buffer[indice];indice++;sprintf(tmp1,"\"%s\" familia=\"classe_laticinio\"",$1);}
		| Palavras_Desconhecidas {sprintf(buffer[indice],"<produto familia=\"desconhecido\">%s</produto>",$1);$$ = buffer[indice];indice++;strcpy(tmp1,$1);}
;

Palavras_Desconhecidas 	: PALAVRA_DESCONHECIDA {sprintf(buffer[indice],"%s",$1);$$ = buffer[indice];indice++;}
			| Palavras_Desconhecidas PALAVRA_DESCONHECIDA {sprintf(buffer[indice],"%s %s",$1,$2);$$ = buffer[indice];indice++;}
;

Palavras_bebidas 	: CLASSE_BEBIDA {sprintf(buffer[indice],"%s",$1);$$ = buffer[indice];indice++;}
			| Palavras_bebidas CLASSE_BEBIDA {sprintf(buffer[indice],"%s %s",$1,$2);$$ = buffer[indice];indice++;}
;

Palavras_classificacao	: PROPRIEDADE_CLASSIFICACAO {sprintf(buffer[indice],"%s",$1);$$ = buffer[indice];indice++;}
			| Palavras_classificacao PROPRIEDADE_CLASSIFICACAO {sprintf(buffer[indice],"%s %s",$1,$2);$$ = buffer[indice];indice++;}
;
		
Palavras_calorias	: PROPRIEDADE_CALORICA {sprintf(buffer[indice],"%s",$1);$$ = buffer[indice];indice++;}
			| Palavras_calorias PROPRIEDADE_CALORICA {sprintf(buffer[indice],"%s %s",$1,$2);$$ = buffer[indice];indice++;}
;

Palavras_sabores	: PROPRIEDADE_SABOR {sprintf(buffer[indice],"%s",$1);$$ = buffer[indice];indice++;}
			| Palavras_sabores PROPRIEDADE_SABOR {sprintf(buffer[indice],"%s %s",$1,$2);$$ = buffer[indice];indice++;}
;

Palavras_gerais	: PROPRIEDADE_GERAL {sprintf(buffer[indice],"%s",$1);$$ = buffer[indice];indice++;}
			| Palavras_gerais PROPRIEDADE_GERAL {sprintf(buffer[indice],"%s %s",$1,$2);$$ = buffer[indice];indice++;}
;
			
Palavras_cores	: PROPRIEDADE_COR {sprintf(buffer[indice],"%s",$1);$$ = buffer[indice];indice++;}
			| Palavras_cores PROPRIEDADE_COR {sprintf(buffer[indice],"%s %s",$1,$2);$$ = buffer[indice];indice++;}
;
			
Palavras_marcas	: PROPRIEDADE_MARCA {sprintf(buffer[indice],"%s",$1);$$ = buffer[indice];indice++;}
			| Palavras_marcas PROPRIEDADE_MARCA {sprintf(buffer[indice],"%s %s",$1,$2);$$ = buffer[indice];indice++;}
;

%%

