package com.kri.kry;

import android.content.Context;

import com.kri.kry.db.DBMain;
import com.kri.kry.db.DBRegex;
import com.kri.kry.db.DBTemplate;
import com.kri.kry.db.DBTemplateType;
import com.kri.kry.utility.Parameter;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by dj on 5/4/16.
 */
public class XMLParser {

    public void SaveData(Context context) throws ParserConfigurationException, SAXException, IOException {

        try {

            String query = "";

            InputStream inputStream = context.getResources().openRawResource(R.raw.template);

            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(inputStream);

            document.getDocumentElement().normalize();

            NodeList nodeList =  document.getElementsByTagName(Parameter.XMLRootNode).item(0).getChildNodes();

            for (int i = 0; i < nodeList.getLength(); i++) {

                if(nodeList.item(i).getNodeName().equals(DBRegex.RegexInfo.TableRegex)) {

                    query = DBRegex.SQL_Insert_Regex;

                    NodeList childNodes = nodeList.item(i).getChildNodes();

                    for(int j = 0; j < childNodes.getLength(); j++)
                    {

                        if(childNodes.item(j).getNodeName().equals(DBRegex.RegexInfo.ColumnID))
                            query = query.replace("[" + DBRegex.RegexInfo.ColumnID + "]", childNodes.item(j).getChildNodes().item(0).getNodeValue().replace("'", "''"));

                        if(childNodes.item(j).getNodeName().equals(DBRegex.RegexInfo.ColumnVersion))
                            query = query.replace("[" + DBRegex.RegexInfo.ColumnVersion + "]", childNodes.item(j).getChildNodes().item(0).getNodeValue().replace("'", "''"));

                        if(childNodes.item(j).getNodeName().equals(DBRegex.RegexInfo.ColumnType))
                            query = query.replace("[" + DBRegex.RegexInfo.ColumnType + "]", childNodes.item(j).getChildNodes().item(0).getNodeValue().replace("'", "''"));

                        if(childNodes.item(j).getNodeName().equals(DBRegex.RegexInfo.ColumnExpression))
                            query = query.replace("[" + DBRegex.RegexInfo.ColumnExpression + "]", childNodes.item(j).getChildNodes().item(0).getNodeValue().replace("'", "''"));

                        if(childNodes.item(j).getNodeName().equals(DBRegex.RegexInfo.ColumnPlaceHolder))
                            query = query.replace("[" + DBRegex.RegexInfo.ColumnPlaceHolder + "]", childNodes.item(j).getChildNodes().item(0).getNodeValue().replace("'", "''"));

                        if(childNodes.item(j).getNodeName().equals(DBRegex.RegexInfo.ColumnWeight))
                            query = query.replace("[" + DBRegex.RegexInfo.ColumnWeight + "]", childNodes.item(j).getChildNodes().item(0).getNodeValue().replace("'", "''"));

                    }
                }

                if(nodeList.item(i).getNodeName().equals(DBTemplateType.TemplateTypeInfo.TableTemplateType)) {

                    query = DBTemplateType.SQL_Insert_TemplateType;

                    NodeList childNodes = nodeList.item(i).getChildNodes();

                    for(int j = 0; j < childNodes.getLength(); j++)
                    {

                        if(childNodes.item(j).getNodeName().equals(DBTemplateType.TemplateTypeInfo.ColumnID))
                            query = query.replace("[" + DBTemplateType.TemplateTypeInfo.ColumnID + "]", childNodes.item(j).getChildNodes().item(0).getNodeValue().replace("'", "''"));

                        if(childNodes.item(j).getNodeName().equals(DBTemplateType.TemplateTypeInfo.ColumnVersion))
                            query = query.replace("[" + DBTemplateType.TemplateTypeInfo.ColumnVersion + "]", childNodes.item(j).getChildNodes().item(0).getNodeValue().replace("'", "''"));

                        if(childNodes.item(j).getNodeName().equals(DBTemplateType.TemplateTypeInfo.ColumnName))
                            query = query.replace("[" + DBTemplateType.TemplateTypeInfo.ColumnName + "]", childNodes.item(j).getChildNodes().item(0).getNodeValue().replace("'", "''"));

                    }
                }

                if(nodeList.item(i).getNodeName().equals(DBTemplate.TemplateInfo.TableTemplate)) {

                    query = DBTemplate.SQL_Insert_Template;

                    NodeList childNodes = nodeList.item(i).getChildNodes();

                    for(int j = 0; j < childNodes.getLength(); j++)
                    {
                        if(childNodes.item(j).getNodeName().equals(DBTemplate.TemplateInfo.ColumnID))
                            query = query.replace("[" + DBTemplate.TemplateInfo.ColumnID + "]", childNodes.item(j).getChildNodes().item(0).getNodeValue().replace("'", "''"));

                        if(childNodes.item(j).getNodeName().equals(DBTemplate.TemplateInfo.ColumnVersion))
                            query = query.replace("[" + DBTemplate.TemplateInfo.ColumnVersion + "]", childNodes.item(j).getChildNodes().item(0).getNodeValue().replace("'", "''"));

                        if(childNodes.item(j).getNodeName().equals(DBTemplate.TemplateInfo.ColumnTypeID))
                            query = query.replace("[" + DBTemplate.TemplateInfo.ColumnTypeID + "]", childNodes.item(j).getChildNodes().item(0).getNodeValue().replace("'", "''"));

                        if(childNodes.item(j).getNodeName().equals(DBTemplate.TemplateInfo.ColumnAddressInfo))
                            query = query.replace("[" + DBTemplate.TemplateInfo.ColumnAddressInfo + "]", childNodes.item(j).getChildNodes().item(0).getNodeValue().replace("'", "''"));

                        if(childNodes.item(j).getNodeName().equals(DBTemplate.TemplateInfo.ColumnAddressData))
                            query = query.replace("[" + DBTemplate.TemplateInfo.ColumnAddressData + "]", childNodes.item(j).getChildNodes().item(0).getNodeValue().replace("'", "''"));

                        if(childNodes.item(j).getNodeName().equals(DBTemplate.TemplateInfo.ColumnTemplateInfo))
                            query = query.replace("[" + DBTemplate.TemplateInfo.ColumnTemplateInfo + "]", childNodes.item(j).getChildNodes().item(0).getNodeValue().replace("'", "''"));

                        if(childNodes.item(j).getNodeName().equals(DBTemplate.TemplateInfo.ColumnTemplateData))
                            query = query.replace("[" + DBTemplate.TemplateInfo.ColumnTemplateData + "]", childNodes.item(j).getChildNodes().item(0).getNodeValue().replace("'", "''"));

                    }
                }

                if(!query.equals("")) {

                    new DBMain(context).WriteData(query);

                    query = "";

                }
            }
        }
        catch (Exception ex) {

            throw ex;

        }
    }
}
