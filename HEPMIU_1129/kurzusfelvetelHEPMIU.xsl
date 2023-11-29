<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:template match="/">
<html> 
<body>
  <h2>UO Kurzusfelvetel</h2>
  <table border="1">
    <tr bgcolor="#9acd32">
      <th style="text-align:left">ID</th>
      <th style="text-align:left">Kurzusnev</th>
      <th style="text-align:left">Kredit</th>
      <th style="text-align:left">Hely</th>
      <th style="text-align:left">Idopont</th>
      <th style="text-align:left">Oktato</th>
    </tr>
    <xsl:for-each select="Hepimu_kurzusfelvetel/kurzusok">
    <tr>
      <td><xsl:value-of select="kurzus id"/></td>
      <td><xsl:value-of select="kurzusnev"/></td>
      <td><xsl:value-of select="kredit"/></td>
      <td><xsl:value-of select="hely"/></td>
      <td><xsl:value-of select="idopont"/></td>
      <td><xsl:value-of select="oktato"/></td>
    </tr>
    </xsl:for-each>
  </table>
</body>
</html>
</xsl:template>
</xsl:stylesheet>