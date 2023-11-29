<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:template match="/">
<html> 
<body>
  <h2>Hallgatok</h2>
  <table border="1">
    <tr bgcolor="#9acd32">
      <th style="text-align:left">ID</th>
      <th style="text-align:left">Oratipus</th>
      <th style="text-align:left">Targy</th>
      <th style="text-align:left">Idopont</th>
      <th style="text-align:left">Helyszin</th>
      <th style="text-align:left">Oktato</th>
      <th style="text-align:left">Szak</th>
    </tr>
    <xsl:for-each select="HEPMIU_orarend/ora">
    <tr>
      <td><xsl:value-of select="ora id"/></td>
      <td><xsl:value-of select="ora tipus"/></td>
      <td><xsl:value-of select="targy"/></td>
      <td><xsl:value-of select="idopont"/></td>
      <td><xsl:value-of select="helyszin"/></td>
      <td><xsl:value-of select="oktato"/></td>
      <td><xsl:value-of select="szak"/></td>
    </tr>
    </xsl:for-each>
  </table>
</body>
</html>
</xsl:template>
</xsl:stylesheet>