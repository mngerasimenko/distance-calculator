# distance-calculator



<P CLASS="western" STYLE="margin-top: 0.42cm; margin-bottom: 0.11cm; line-height: 0.18cm">
<FONT FACE="Arial, sans-serif"><FONT SIZE=3 STYLE="font-size: 13pt"><B>Оформление
результата</B></FONT></FONT></P>
<UL>
	<LI><P CLASS="western" STYLE="margin-bottom: 0cm; ">
	<FONT FACE="Times New Roman, serif">Отправляется как один zip-архив
	с git-репозитарием или ссылка на репозитарий на github.com.</FONT></P>
	<LI><P CLASS="western" STYLE="margin-bottom: 0cm;">
	<FONT FACE="Times New Roman, serif">В проекте в папке docs
	документация (при необходимости), в папке liquibase Liquibase и
	миграции к нему.</FONT></P>
	<LI><P CLASS="western" STYLE="margin-bottom: 0cm;">
	<FONT FACE="Times New Roman, serif">Проект собирается Maven.</FONT></P>
	<LI><P CLASS="western" STYLE="margin-bottom: 0cm;">
	<FONT FACE="Times New Roman, serif">Для тестового задания
	предполагается логин/пароль MySQL root/root, имя базы данных
	distance-calculator.</FONT></P>
	<LI><P CLASS="western" STYLE="margin-bottom: 0cm;">
	<FONT COLOR="#000000"><FONT FACE="Times New Roman, serif"><SPAN LANG="en-US">Data
	source </SPAN></FONT></FONT><FONT COLOR="#000000"><FONT FACE="Times New Roman, serif">в</FONT></FONT><FONT COLOR="#000000"><FONT FACE="Times New Roman, serif"><SPAN LANG="en-US">
	WildFly java:/magenta/datasource/test-distance-calculator .</SPAN></FONT></FONT></P>
</UL>
<P LANG="en-US" CLASS="western" STYLE="margin-top: 0.42cm; margin-bottom: 0.11cm;">
<FONT FACE="Arial, sans-serif"><FONT SIZE=3 STYLE="font-size: 13pt"><B>Overview</B></FONT></FONT></P>
<P LANG="en-US" CLASS="western" STYLE="margin-bottom: 0cm;">
<FONT FACE="Times New Roman, serif">Design and implement web service
(REST) application for distance calculation:</FONT></P>
<UL>
	<LI><P CLASS="western" STYLE="margin-bottom: 0cm;">
	<FONT FACE="Times New Roman, serif">Database holds two entities:</FONT></P>
	<UL>
		<LI><P CLASS="western" STYLE="margin-bottom: 0cm;">
		<FONT FACE="Times New Roman, serif">City</FONT></P>
		<UL>
			<LI><P CLASS="western" STYLE="margin-bottom: 0cm;">
			<FONT FACE="Times New Roman, serif">Name</FONT></P>
			<LI><P CLASS="western" STYLE="margin-bottom: 0cm;">
			<FONT FACE="Times New Roman, serif">Latitude</FONT></P>
			<LI><P CLASS="western" STYLE="margin-bottom: 0cm;">
			<FONT FACE="Times New Roman, serif">Longitude</FONT></P>
		</UL>
		<LI><P CLASS="western" STYLE="margin-bottom: 0cm;">
		<FONT FACE="Times New Roman, serif">Distance</FONT></P>
		<UL>
			<LI><P CLASS="western" STYLE="margin-bottom: 0cm;">
			<FONT FACE="Times New Roman, serif">From city</FONT></P>
			<LI><P CLASS="western" STYLE="margin-bottom: 0cm;">
			<FONT FACE="Times New Roman, serif">To city</FONT></P>
			<LI><P CLASS="western" STYLE="margin-bottom: 0cm;">
			<FONT FACE="Times New Roman, serif">Distance</FONT></P>
		</UL>
	</UL>
	<LI><P LANG="en-US" CLASS="western" STYLE="margin-bottom: 0cm;">
	<FONT FACE="Times New Roman, serif">Application should make it
	possible to calculate the distance in two ways:</FONT></P>
	<UL>
		<LI><P CLASS="western" STYLE="margin-bottom: 0cm;">
		<FONT COLOR="#000000">&ldquo;<FONT FACE="Times New Roman, serif"><SPAN LANG="en-US">Crowflight&rdquo;
		(straight distance) between cities. Lookup formula for distance
		calculation on the sphere in the internet.</SPAN></FONT></FONT></P>
		<LI><P LANG="en-US" CLASS="western" STYLE="margin-bottom: 0cm;">
		<FONT FACE="Times New Roman, serif">Lookup distance between two
		cities via &ldquo;distance matrix&rdquo; (distance table in the
		database)</FONT></P>
	</UL>
	<LI><P CLASS="western" STYLE="margin-bottom: 0cm;">
	<FONT FACE="Times New Roman, serif">API has 3 endpoints:</FONT></P>
	<UL>
		<LI><P CLASS="western" STYLE="margin-bottom: 0cm;">
		<FONT COLOR="#000000"><FONT FACE="Times New Roman, serif"><SPAN LANG="en-US">List
		of all cities in the DB. </SPAN></FONT></FONT><FONT COLOR="#000000"><FONT FACE="Times New Roman, serif">Fields:</FONT></FONT></P>
		<UL>
			<LI><P CLASS="western" STYLE="margin-bottom: 0cm;">
			<FONT FACE="Times New Roman, serif">ID</FONT></P>
			<LI><P CLASS="western" STYLE="margin-bottom: 0cm;">
			<FONT FACE="Times New Roman, serif">Name</FONT></P>
		</UL>
		<LI><P CLASS="western" STYLE="margin-bottom: 0cm;">
		<FONT FACE="Times New Roman, serif">Calculate distance</FONT></P>
		<UL>
			<LI><P CLASS="western" STYLE="margin-bottom: 0cm;">
			<FONT FACE="Times New Roman, serif">Input:</FONT></P>
		</UL>
	</UL>
</UL>
<UL>
	<UL>
		<UL>
			<UL>
				<LI><P LANG="en-US" CLASS="western" STYLE="margin-bottom: 0cm;">
				<FONT FACE="Times New Roman, serif">Calculation Type:
				&lt;Crowflight, Distance Matrix, All&gt;</FONT></P>
				<LI><P LANG="en-US" CLASS="western" STYLE="margin-bottom: 0cm;">
				<FONT FACE="Times New Roman, serif">From City: &lt;List of
				cities&gt;</FONT></P>
				<LI><P LANG="en-US" CLASS="western" STYLE="margin-bottom: 0cm;">
				<FONT FACE="Times New Roman, serif">To City: &lt;List of Cities&gt;</FONT></P>
			</UL>
			<LI><P CLASS="western" STYLE="margin-bottom: 0cm;">
			<FONT FACE="Times New Roman, serif">Output:</FONT></P>
			<UL>
				<LI><P LANG="en-US" CLASS="western" STYLE="margin-bottom: 0cm;">
				<FONT FACE="Times New Roman, serif">Results: all distance
				calculation results as requested</FONT></P>
			</UL>
		</UL>
		<LI><P CLASS="western" STYLE="margin-bottom: 0cm;">
		<FONT COLOR="#000000"><FONT FACE="Times New Roman, serif"><SPAN LANG="en-US">Upload
		data to the DB. Uploads XML file with cities and distances into the
		application. </SPAN></FONT></FONT><FONT COLOR="#000000"><FONT FACE="Times New Roman, serif">Application
		parses it and stores it into the database.</FONT></FONT></P>
		<UL>
			<LI><P CLASS="western" STYLE="margin-bottom: 0cm;">
			<FONT FACE="Times New Roman, serif">Input:</FONT></P>
			<UL>
				<LI><P CLASS="western" STYLE="margin-bottom: 0cm;">
				<FONT COLOR="#000000"><FONT FACE="Arial, sans-serif"><SPAN LANG="en-US">Multipart/form-data
				form submission with </SPAN></FONT></FONT><FONT COLOR="#000000"><FONT FACE="Times New Roman, serif"><SPAN LANG="en-US">single
				&ldquo;File&rdquo; input.</SPAN></FONT></FONT></P>
			</UL>
			<LI><P CLASS="western" STYLE="margin-bottom: 0cm;">
			<FONT FACE="Times New Roman, serif">Output:</FONT></P>
			<UL>
				<LI><P CLASS="western" STYLE="margin-bottom: 0cm;">
				<FONT FACE="Times New Roman, serif">HTTP response code 200
				without body</FONT></P>
			</UL>
		</UL>
	</UL>
</UL>
<P CLASS="western" STYLE="margin-top: 0.42cm; margin-bottom: 0.11cm;">
<U>       </U>
</P>
<P CLASS="western" STYLE="margin-top: 0.42cm; margin-bottom: 0.11cm;">
<FONT FACE="Arial, sans-serif"><FONT SIZE=3 STYLE="font-size: 13pt"><B>Tools/Libraries</B></FONT></FONT></P>
<UL>
	<LI><P CLASS="western" STYLE="margin-bottom: 0cm;">
	<FONT FACE="Times New Roman, serif">IDEA Community Edition</FONT></P>
	<LI><P CLASS="western" STYLE="margin-bottom: 0cm;">
	<FONT FACE="Times New Roman, serif">Git</FONT></P>
	<LI><P CLASS="western" STYLE="margin-bottom: 0cm;">
	<FONT FACE="Times New Roman, serif">Maven</FONT></P>
	<LI><P CLASS="western" STYLE="margin-bottom: 0cm;">
	<FONT FACE="Times New Roman, serif">MySQL DB</FONT></P>
	<LI><P CLASS="western" STYLE="margin-bottom: 0cm;">
	<FONT FACE="Times New Roman, serif">Liquibase для миграции к
	структуре DB</FONT></P>
	<LI><P CLASS="western" STYLE="margin-bottom: 0cm;">
	<FONT FACE="Times New Roman, serif">Java 8 (можно использовать более
	свежие версии)</FONT></P>
	<LI><P LANG="en-US" CLASS="western" STYLE="margin-bottom: 0cm;">
	<FONT FACE="Times New Roman, serif">JAXB</FONT></P>
</UL>
<P CLASS="western" STYLE="margin-left: 1.25cm; margin-bottom: 0cm;">
<FONT COLOR="#000000"><FONT FACE="Times New Roman, serif">Выбрать
одно из двух</FONT></FONT><FONT COLOR="#000000"><FONT FACE="Times New Roman, serif"><SPAN LANG="en-US">:</SPAN></FONT></FONT></P>
<UL>
	<LI><P LANG="en-US" CLASS="western" STYLE="margin-bottom: 0cm;">
	<FONT FACE="Times New Roman, serif">JEE 8 + WildFly 14</FONT></P>
	<LI><P LANG="en-US" CLASS="western" STYLE="margin-bottom: 0cm;">
	<FONT FACE="Times New Roman, serif">Spring + Tomcat</FONT></P>
</UL>
<P CLASS="western" STYLE="margin-top: 0.42cm; margin-bottom: 0.11cm; line-height: 0.18cm"><A NAME="_GoBack"></A>
<BR><BR>
</P>
<P CLASS="western" STYLE="margin-top: 0.42cm; margin-bottom: 0.11cm;">
<FONT FACE="Arial, sans-serif"><FONT SIZE=3 STYLE="font-size: 13pt"><B>Requirements</B></FONT></FONT></P>
<UL>
	<LI><P LANG="en-US" CLASS="western" STYLE="margin-bottom: 0cm;">
	<FONT FACE="Times New Roman, serif">Use Java exceptions to indicate
	that distance cannot be calculated (for example, it is not in the
	distance table).</FONT></P>
	<LI><P LANG="en-US" CLASS="western" STYLE="margin-bottom: 0cm;">
	<FONT FACE="Times New Roman, serif">Make sure you are up to speed on
	the following Java basics: interfaces, classes, inheritance,
	overriding, collections.</FONT></P>
</UL>
<P LANG="en-US" CLASS="western" STYLE="margin-top: 0.42cm; margin-bottom: 0.11cm;">
<FONT FACE="Arial, sans-serif"><FONT SIZE=3 STYLE="font-size: 13pt"><B>Optional
Requirements</B></FONT></FONT></P>
<P LANG="en-US" CLASS="western" STYLE="margin-bottom: 0cm;">
<FONT FACE="Times New Roman, serif">The following requirements are
optional. Please work on them if you have capacity (after you
submitted result of your assignment back to us):</FONT></P>
<UL>
	<LI><P CLASS="western" STYLE="margin-bottom: 0cm;">
	<FONT COLOR="#000000"><FONT FACE="Times New Roman, serif"><SPAN LANG="en-US">Test
	if your application would scale to 10.000 cities and 1.000.000
	entries in distance table (assume that distance is defined only for
	some cities in the distance matrix). Test if XML file of this size
	can be loaded fine. </SPAN></FONT></FONT><FONT COLOR="#000000"><FONT FACE="Times New Roman, serif">Fix
	scale/performance issues if they would appear. </FONT></FONT>
	</P>
</UL>
<P CLASS="western" STYLE="margin-bottom: 0.28cm"><BR><BR>
</P>
