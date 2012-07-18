#!/bin/bash

DS_A="BD-A_source.txt" #default data source
DS_A_XML="BD-A_source.xml" #default xml output

DS_B="BD-B_source.txt" #default data source
DS_B_XML="BD-B_source.xml" #default xml output

clear

#-------------------------------------------------------------------------------#
#			Limpar ficheiros anteriores				#
#-------------------------------------------------------------------------------#
echo "----- Limpar ficheiros anteriores -----"
rm -f *.exe lex.yy.c y.tab.c log.txt BD-A_clean.txt BD-B_clean.txt BD-A_source.xml BD-B_source.txt output.xml

#-------------------------------------------------------------------------------#
#				LER DATA SOURCE					#
#-------------------------------------------------------------------------------# 
#echo "Insira o nome da fonte de dados A e pressione [ENTER]:" 
#read DS_A
#echo " Ficheiro inserido: $DS_A"
#echo
#echo "----- Texto usado para input -----"
#cat input.txt

#-------------------------------------------------------------------------------#
#				CLEAN DATA SOURCE				#
#-------------------------------------------------------------------------------# 
echo "----- Cleaning data -----"
cp -f BD-A_source.txt CleaningData/bin/
cp -f BD-B_source.txt CleaningData/bin/
cd CleaningData/bin/
java pt.ipl.estg.dei.miecm.rc.Main
cp -f BD-A_clean.txt ../../
cp -f BD-B_clean.txt ../../
cd ../../
clear

# FAZER O CLEAN AOS DOIS DATA SOURCES

#-------------------------------------------------------------------------------#
#		Sintaxe e Gramatica - COMPILAR LEX & YACC			#
#-------------------------------------------------------------------------------#
echo
echo "----- Compilar recursos.lex -----"
flex recursos.lex
echo
echo "----- Compilar recursos.yacc -----"
bison -yd recursos.yacc
echo
echo "----- Compilar/linkar recursos.lex/recursos.yacc -----"
cc lex.yy.c y.tab.c -o recursos.exe
chmod 700 recursos.exe


#-------------------------------------------------------------------------------#
# 			PROCESSAR CADA LINHA DO DATA SOURCE			#
#-------------------------------------------------------------------------------#
clear
# construir o nome do ficheiro
str=$( echo $DS_A | tr " " "_")
str=$( echo $str | tr "." " ")
myarr=($str)
DS_A_XML=${myarr[0]}".xml"
#echo $DS_A_XML

rm -f $DS_A_XML input.txt
echo "<xml version=\"1.0\" encoding=\"utf-8\">" > $DS_A_XML
a=0

# executar yacc e lex para cada linha
#echo $DS_A
while read line; do 
	let a+=1
	size="${#line}"
	if [ $size -gt 0 ]; then
		#echo $size
		echo $line > input.txt
		./recursos.exe < input.txt >> $DS_A_XML
	fi
	
	# debug - limit of rows to process
	#if [ $a -gt 21 ]; then
	#	break;
	#fi
done < "BD-A_clean.txt" # $DS_A I can't use it to read dynamic file...
echo "</xml>" >> $DS_A_XML


rm -f $DS_B_XML input.txt
echo "<xml version=\"1.0\" encoding=\"utf-8\">" > $DS_B_XML
a=0

# executar yacc e lex para cada linha
#echo $DS_A
while read line; do 
	let a+=1
	size="${#line}"
	if [ $size -gt 0 ]; then
		#echo $size
		echo $line > input.txt
		./recursos.exe < input.txt >> $DS_B_XML
	fi
	
	# debug - limit of rows to process
	#if [ $a -gt 40 ]; then
	#	break;
	#fi
done < "BD-B_clean.txt" # $DS_A I can't use it to read dynamic file...
echo "</xml>" >> $DS_B_XML

echo
echo
echo "Numero total de linhas processadas: $a"
echo

#-------------------------------------------------------------------------------#
#				ToEqual - MATCH PRODUCTS			#
#-------------------------------------------------------------------------------# 
echo "----- ToEquals -----"
cp -f BD-A_source.xml SWEquals/bin/
cp -f BD-B_source.xml SWEquals/bin/
cd SWEquals/bin/
java ipl.estg.dei.meicm.rc.Main
cp output.xml ../../
cd ../../
clear
