//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v1.0.4-b18-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2005.08.23 at 10:44:28 GMT+05:30 
//


package edu.wustl.common.cde.xml.impl;

public class XMLPermissibleValueTypeImpl implements edu.wustl.common.cde.xml.XMLPermissibleValueType, com.sun.xml.bind.JAXBObject, edu.wustl.common.cde.xml.impl.runtime.UnmarshallableObject, edu.wustl.common.cde.xml.impl.runtime.XMLSerializable, edu.wustl.common.cde.xml.impl.runtime.ValidatableObject
{

    protected boolean has_DepthOfHierarchyTree;
    protected int _DepthOfHierarchyTree;
    protected java.lang.String _EvsTerminology;
    protected java.lang.String _ParentConceptCode;
    protected java.lang.String _ConceptCode;
    public final static java.lang.Class version = (edu.wustl.common.cde.xml.impl.JAXBVersion.class);
    private static com.sun.msv.grammar.Grammar schemaFragment;

    private final static java.lang.Class PRIMARY_INTERFACE_CLASS() {
        return (edu.wustl.common.cde.xml.XMLPermissibleValueType.class);
    }

    public int getDepthOfHierarchyTree() {
        return _DepthOfHierarchyTree;
    }

    public void setDepthOfHierarchyTree(int value) {
        _DepthOfHierarchyTree = value;
        has_DepthOfHierarchyTree = true;
    }

    public java.lang.String getEvsTerminology() {
        return _EvsTerminology;
    }

    public void setEvsTerminology(java.lang.String value) {
        _EvsTerminology = value;
    }

    public java.lang.String getParentConceptCode() {
        return _ParentConceptCode;
    }

    public void setParentConceptCode(java.lang.String value) {
        _ParentConceptCode = value;
    }

    public java.lang.String getConceptCode() {
        return _ConceptCode;
    }

    public void setConceptCode(java.lang.String value) {
        _ConceptCode = value;
    }

    public edu.wustl.common.cde.xml.impl.runtime.UnmarshallingEventHandler createUnmarshaller(edu.wustl.common.cde.xml.impl.runtime.UnmarshallingContext context) {
        return new edu.wustl.common.cde.xml.impl.XMLPermissibleValueTypeImpl.Unmarshaller(context);
    }

    public void serializeBody(edu.wustl.common.cde.xml.impl.runtime.XMLSerializer context)
        throws org.xml.sax.SAXException
    {
        if (!has_DepthOfHierarchyTree) {
            context.reportError(com.sun.xml.bind.serializer.Util.createMissingObjectError(this, "DepthOfHierarchyTree"));
        }
        context.startElement("", "evsTerminology");
        context.endNamespaceDecls();
        context.endAttributes();
        try {
            context.text(((java.lang.String) _EvsTerminology), "EvsTerminology");
        } catch (java.lang.Exception e) {
            edu.wustl.common.cde.xml.impl.runtime.Util.handlePrintConversionException(this, e, context);
        }
        context.endElement();
        context.startElement("", "conceptCode");
        context.endNamespaceDecls();
        context.endAttributes();
        try {
            context.text(((java.lang.String) _ConceptCode), "ConceptCode");
        } catch (java.lang.Exception e) {
            edu.wustl.common.cde.xml.impl.runtime.Util.handlePrintConversionException(this, e, context);
        }
        context.endElement();
        context.startElement("", "parentConceptCode");
        context.endNamespaceDecls();
        context.endAttributes();
        try {
            context.text(((java.lang.String) _ParentConceptCode), "ParentConceptCode");
        } catch (java.lang.Exception e) {
            edu.wustl.common.cde.xml.impl.runtime.Util.handlePrintConversionException(this, e, context);
        }
        context.endElement();
        context.startElement("", "depthOfHierarchyTree");
        context.endNamespaceDecls();
        context.endAttributes();
        try {
            context.text(javax.xml.bind.DatatypeConverter.printInt(((int) _DepthOfHierarchyTree)), "DepthOfHierarchyTree");
        } catch (java.lang.Exception e) {
            edu.wustl.common.cde.xml.impl.runtime.Util.handlePrintConversionException(this, e, context);
        }
        context.endElement();
    }

    public void serializeAttributes(edu.wustl.common.cde.xml.impl.runtime.XMLSerializer context)
        throws org.xml.sax.SAXException
    {
        if (!has_DepthOfHierarchyTree) {
            context.reportError(com.sun.xml.bind.serializer.Util.createMissingObjectError(this, "DepthOfHierarchyTree"));
        }
    }

    public void serializeURIs(edu.wustl.common.cde.xml.impl.runtime.XMLSerializer context)
        throws org.xml.sax.SAXException
    {
        if (!has_DepthOfHierarchyTree) {
            context.reportError(com.sun.xml.bind.serializer.Util.createMissingObjectError(this, "DepthOfHierarchyTree"));
        }
    }

    public java.lang.Class getPrimaryInterface() {
        return (edu.wustl.common.cde.xml.XMLPermissibleValueType.class);
    }

    public com.sun.msv.verifier.DocumentDeclaration createRawValidator() {
        if (schemaFragment == null) {
            schemaFragment = com.sun.xml.bind.validator.SchemaDeserializer.deserialize((
 "\u00ac\u00ed\u0000\u0005sr\u0000\u001fcom.sun.msv.grammar.SequenceExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000\u001dcom.su"
+"n.msv.grammar.BinaryExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002L\u0000\u0004exp1t\u0000 Lcom/sun/msv/gra"
+"mmar/Expression;L\u0000\u0004exp2q\u0000~\u0000\u0002xr\u0000\u001ecom.sun.msv.grammar.Expressi"
+"on\u00f8\u0018\u0082\u00e8N5~O\u0002\u0000\u0002L\u0000\u0013epsilonReducibilityt\u0000\u0013Ljava/lang/Boolean;L\u0000\u000b"
+"expandedExpq\u0000~\u0000\u0002xpppsq\u0000~\u0000\u0000ppsq\u0000~\u0000\u0000ppsr\u0000\'com.sun.msv.grammar."
+"trex.ElementPattern\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001L\u0000\tnameClasst\u0000\u001fLcom/sun/msv/gr"
+"ammar/NameClass;xr\u0000\u001ecom.sun.msv.grammar.ElementExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000"
+"\u0002Z\u0000\u001aignoreUndeclaredAttributesL\u0000\fcontentModelq\u0000~\u0000\u0002xq\u0000~\u0000\u0003pp\u0000s"
+"q\u0000~\u0000\u0000ppsr\u0000\u001bcom.sun.msv.grammar.DataExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0003L\u0000\u0002dtt\u0000\u001fLor"
+"g/relaxng/datatype/Datatype;L\u0000\u0006exceptq\u0000~\u0000\u0002L\u0000\u0004namet\u0000\u001dLcom/sun"
+"/msv/util/StringPair;xq\u0000~\u0000\u0003ppsr\u0000#com.sun.msv.datatype.xsd.St"
+"ringType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001Z\u0000\risAlwaysValidxr\u0000*com.sun.msv.datatype."
+"xsd.BuiltinAtomicType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000%com.sun.msv.datatype.xsd"
+".ConcreteType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000\'com.sun.msv.datatype.xsd.XSDatat"
+"ypeImpl\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0003L\u0000\fnamespaceUrit\u0000\u0012Ljava/lang/String;L\u0000\btyp"
+"eNameq\u0000~\u0000\u0015L\u0000\nwhiteSpacet\u0000.Lcom/sun/msv/datatype/xsd/WhiteSpa"
+"ceProcessor;xpt\u0000 http://www.w3.org/2001/XMLSchemat\u0000\u0006stringsr"
+"\u00005com.sun.msv.datatype.xsd.WhiteSpaceProcessor$Preserve\u0000\u0000\u0000\u0000\u0000"
+"\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000,com.sun.msv.datatype.xsd.WhiteSpaceProcessor\u0000\u0000\u0000\u0000\u0000\u0000"
+"\u0000\u0001\u0002\u0000\u0000xp\u0001sr\u00000com.sun.msv.grammar.Expression$NullSetExpression"
+"\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0003ppsr\u0000\u001bcom.sun.msv.util.StringPair\u00d0t\u001ejB\u008f\u008d\u00a0\u0002\u0000"
+"\u0002L\u0000\tlocalNameq\u0000~\u0000\u0015L\u0000\fnamespaceURIq\u0000~\u0000\u0015xpq\u0000~\u0000\u0019q\u0000~\u0000\u0018sr\u0000\u001dcom.su"
+"n.msv.grammar.ChoiceExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0001ppsr\u0000 com.sun.msv.gr"
+"ammar.AttributeExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002L\u0000\u0003expq\u0000~\u0000\u0002L\u0000\tnameClassq\u0000~\u0000\txq\u0000"
+"~\u0000\u0003sr\u0000\u0011java.lang.Boolean\u00cd r\u0080\u00d5\u009c\u00fa\u00ee\u0002\u0000\u0001Z\u0000\u0005valuexp\u0000psq\u0000~\u0000\rppsr\u0000\"c"
+"om.sun.msv.datatype.xsd.QnameType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0012q\u0000~\u0000\u0018t\u0000\u0005QN"
+"amesr\u00005com.sun.msv.datatype.xsd.WhiteSpaceProcessor$Collapse"
+"\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u001bq\u0000~\u0000\u001esq\u0000~\u0000\u001fq\u0000~\u0000*q\u0000~\u0000\u0018sr\u0000#com.sun.msv.gramma"
+"r.SimpleNameClass\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002L\u0000\tlocalNameq\u0000~\u0000\u0015L\u0000\fnamespaceURI"
+"q\u0000~\u0000\u0015xr\u0000\u001dcom.sun.msv.grammar.NameClass\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xpt\u0000\u0004typet\u0000"
+")http://www.w3.org/2001/XMLSchema-instancesr\u00000com.sun.msv.gr"
+"ammar.Expression$EpsilonExpression\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0003sq\u0000~\u0000%\u0001ps"
+"q\u0000~\u0000.t\u0000\u000eevsTerminologyt\u0000\u0000sq\u0000~\u0000\bpp\u0000sq\u0000~\u0000\u0000ppq\u0000~\u0000\u0010sq\u0000~\u0000!ppsq\u0000~\u0000"
+"#q\u0000~\u0000&pq\u0000~\u0000\'q\u0000~\u00000q\u0000~\u00004sq\u0000~\u0000.t\u0000\u000bconceptCodeq\u0000~\u00008sq\u0000~\u0000\bpp\u0000sq\u0000~"
+"\u0000\u0000ppq\u0000~\u0000\u0010sq\u0000~\u0000!ppsq\u0000~\u0000#q\u0000~\u0000&pq\u0000~\u0000\'q\u0000~\u00000q\u0000~\u00004sq\u0000~\u0000.t\u0000\u0011parentC"
+"onceptCodeq\u0000~\u00008sq\u0000~\u0000\bpp\u0000sq\u0000~\u0000\u0000ppsq\u0000~\u0000\rppsr\u0000 com.sun.msv.data"
+"type.xsd.IntType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000+com.sun.msv.datatype.xsd.Inte"
+"gerDerivedType\u0099\u00f1]\u0090&6k\u00be\u0002\u0000\u0001L\u0000\nbaseFacetst\u0000)Lcom/sun/msv/dataty"
+"pe/xsd/XSDatatypeImpl;xq\u0000~\u0000\u0012q\u0000~\u0000\u0018t\u0000\u0003intq\u0000~\u0000,sr\u0000*com.sun.msv."
+"datatype.xsd.MaxInclusiveFacet\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000#com.sun.msv.dat"
+"atype.xsd.RangeFacet\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001L\u0000\nlimitValuet\u0000\u0012Ljava/lang/Ob"
+"ject;xr\u00009com.sun.msv.datatype.xsd.DataTypeWithValueConstrain"
+"tFacet\"\u00a7Ro\u00ca\u00c7\u008aT\u0002\u0000\u0000xr\u0000*com.sun.msv.datatype.xsd.DataTypeWithFa"
+"cet\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0005Z\u0000\fisFacetFixedZ\u0000\u0012needValueCheckFlagL\u0000\bbaseTyp"
+"eq\u0000~\u0000JL\u0000\fconcreteTypet\u0000\'Lcom/sun/msv/datatype/xsd/ConcreteTy"
+"pe;L\u0000\tfacetNameq\u0000~\u0000\u0015xq\u0000~\u0000\u0014ppq\u0000~\u0000,\u0000\u0001sr\u0000*com.sun.msv.datatype."
+"xsd.MinInclusiveFacet\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000Nppq\u0000~\u0000,\u0000\u0000sr\u0000!com.sun.m"
+"sv.datatype.xsd.LongType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000Iq\u0000~\u0000\u0018t\u0000\u0004longq\u0000~\u0000,sq"
+"\u0000~\u0000Mppq\u0000~\u0000,\u0000\u0001sq\u0000~\u0000Tppq\u0000~\u0000,\u0000\u0000sr\u0000$com.sun.msv.datatype.xsd.Int"
+"egerType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000Iq\u0000~\u0000\u0018t\u0000\u0007integerq\u0000~\u0000,sr\u0000,com.sun.msv"
+".datatype.xsd.FractionDigitsFacet\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001I\u0000\u0005scalexr\u0000;com."
+"sun.msv.datatype.xsd.DataTypeWithLexicalConstraintFacetT\u0090\u001c>\u001a"
+"zb\u00ea\u0002\u0000\u0000xq\u0000~\u0000Qppq\u0000~\u0000,\u0001\u0000sr\u0000#com.sun.msv.datatype.xsd.NumberType"
+"\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0012q\u0000~\u0000\u0018t\u0000\u0007decimalq\u0000~\u0000,q\u0000~\u0000bt\u0000\u000efractionDigits\u0000"
+"\u0000\u0000\u0000q\u0000~\u0000\\t\u0000\fminInclusivesr\u0000\u000ejava.lang.Long;\u008b\u00e4\u0090\u00cc\u008f#\u00df\u0002\u0000\u0001J\u0000\u0005value"
+"xr\u0000\u0010java.lang.Number\u0086\u00ac\u0095\u001d\u000b\u0094\u00e0\u008b\u0002\u0000\u0000xp\u0080\u0000\u0000\u0000\u0000\u0000\u0000\u0000q\u0000~\u0000\\t\u0000\fmaxInclusiv"
+"esq\u0000~\u0000f\u007f\u00ff\u00ff\u00ff\u00ff\u00ff\u00ff\u00ffq\u0000~\u0000Wq\u0000~\u0000esr\u0000\u0011java.lang.Integer\u0012\u00e2\u00a0\u00a4\u00f7\u0081\u00878\u0002\u0000\u0001I\u0000\u0005"
+"valuexq\u0000~\u0000g\u0080\u0000\u0000\u0000q\u0000~\u0000Wq\u0000~\u0000isq\u0000~\u0000k\u007f\u00ff\u00ff\u00ffq\u0000~\u0000\u001esq\u0000~\u0000\u001fq\u0000~\u0000Lq\u0000~\u0000\u0018sq\u0000~"
+"\u0000!ppsq\u0000~\u0000#q\u0000~\u0000&pq\u0000~\u0000\'q\u0000~\u00000q\u0000~\u00004sq\u0000~\u0000.t\u0000\u0014depthOfHierarchyTree"
+"q\u0000~\u00008sr\u0000\"com.sun.msv.grammar.ExpressionPool\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001L\u0000\bexp"
+"Tablet\u0000/Lcom/sun/msv/grammar/ExpressionPool$ClosedHash;xpsr\u0000"
+"-com.sun.msv.grammar.ExpressionPool$ClosedHash\u00d7j\u00d0N\u00ef\u00e8\u00ed\u001c\u0003\u0000\u0003I\u0000\u0005"
+"countB\u0000\rstreamVersionL\u0000\u0006parentt\u0000$Lcom/sun/msv/grammar/Expres"
+"sionPool;xp\u0000\u0000\u0000\u000b\u0001pq\u0000~\u0000Aq\u0000~\u0000\u0007q\u0000~\u0000oq\u0000~\u0000\u0006q\u0000~\u0000\fq\u0000~\u0000:q\u0000~\u0000@q\u0000~\u0000\u0005q\u0000~"
+"\u0000Fq\u0000~\u0000\"q\u0000~\u0000;x"));
        }
        return new com.sun.msv.verifier.regexp.REDocumentDeclaration(schemaFragment);
    }

    public class Unmarshaller
        extends edu.wustl.common.cde.xml.impl.runtime.AbstractUnmarshallingEventHandlerImpl
    {


        public Unmarshaller(edu.wustl.common.cde.xml.impl.runtime.UnmarshallingContext context) {
            super(context, "-------------");
        }

        protected Unmarshaller(edu.wustl.common.cde.xml.impl.runtime.UnmarshallingContext context, int startState) {
            this(context);
            state = startState;
        }

        public java.lang.Object owner() {
            return edu.wustl.common.cde.xml.impl.XMLPermissibleValueTypeImpl.this;
        }

        public void enterElement(java.lang.String ___uri, java.lang.String ___local, java.lang.String ___qname, org.xml.sax.Attributes __atts)
            throws org.xml.sax.SAXException
        {
            int attIdx;
            outer:
            while (true) {
                switch (state) {
                    case  0 :
                        if (("evsTerminology" == ___local)&&("" == ___uri)) {
                            context.pushAttributes(__atts, true);
                            state = 1;
                            return ;
                        }
                        break;
                    case  9 :
                        if (("depthOfHierarchyTree" == ___local)&&("" == ___uri)) {
                            context.pushAttributes(__atts, true);
                            state = 10;
                            return ;
                        }
                        break;
                    case  3 :
                        if (("conceptCode" == ___local)&&("" == ___uri)) {
                            context.pushAttributes(__atts, true);
                            state = 4;
                            return ;
                        }
                        break;
                    case  12 :
                        revertToParentFromEnterElement(___uri, ___local, ___qname, __atts);
                        return ;
                    case  6 :
                        if (("parentConceptCode" == ___local)&&("" == ___uri)) {
                            context.pushAttributes(__atts, true);
                            state = 7;
                            return ;
                        }
                        break;
                }
                super.enterElement(___uri, ___local, ___qname, __atts);
                break;
            }
        }

        public void leaveElement(java.lang.String ___uri, java.lang.String ___local, java.lang.String ___qname)
            throws org.xml.sax.SAXException
        {
            int attIdx;
            outer:
            while (true) {
                switch (state) {
                    case  5 :
                        if (("conceptCode" == ___local)&&("" == ___uri)) {
                            context.popAttributes();
                            state = 6;
                            return ;
                        }
                        break;
                    case  2 :
                        if (("evsTerminology" == ___local)&&("" == ___uri)) {
                            context.popAttributes();
                            state = 3;
                            return ;
                        }
                        break;
                    case  11 :
                        if (("depthOfHierarchyTree" == ___local)&&("" == ___uri)) {
                            context.popAttributes();
                            state = 12;
                            return ;
                        }
                        break;
                    case  12 :
                        revertToParentFromLeaveElement(___uri, ___local, ___qname);
                        return ;
                    case  8 :
                        if (("parentConceptCode" == ___local)&&("" == ___uri)) {
                            context.popAttributes();
                            state = 9;
                            return ;
                        }
                        break;
                }
                super.leaveElement(___uri, ___local, ___qname);
                break;
            }
        }

        public void enterAttribute(java.lang.String ___uri, java.lang.String ___local, java.lang.String ___qname)
            throws org.xml.sax.SAXException
        {
            int attIdx;
            outer:
            while (true) {
                switch (state) {
                    case  12 :
                        revertToParentFromEnterAttribute(___uri, ___local, ___qname);
                        return ;
                }
                super.enterAttribute(___uri, ___local, ___qname);
                break;
            }
        }

        public void leaveAttribute(java.lang.String ___uri, java.lang.String ___local, java.lang.String ___qname)
            throws org.xml.sax.SAXException
        {
            int attIdx;
            outer:
            while (true) {
                switch (state) {
                    case  12 :
                        revertToParentFromLeaveAttribute(___uri, ___local, ___qname);
                        return ;
                }
                super.leaveAttribute(___uri, ___local, ___qname);
                break;
            }
        }

        public void handleText(final java.lang.String value)
            throws org.xml.sax.SAXException
        {
            int attIdx;
            outer:
            while (true) {
                try {
                    switch (state) {
                        case  4 :
                            eatText1(value);
                            state = 5;
                            return ;
                        case  7 :
                            eatText2(value);
                            state = 8;
                            return ;
                        case  1 :
                            eatText3(value);
                            state = 2;
                            return ;
                        case  12 :
                            revertToParentFromText(value);
                            return ;
                        case  10 :
                            eatText4(value);
                            state = 11;
                            return ;
                    }
                } catch (java.lang.RuntimeException e) {
                    handleUnexpectedTextException(value, e);
                }
                break;
            }
        }

        private void eatText1(final java.lang.String value)
            throws org.xml.sax.SAXException
        {
            try {
                _ConceptCode = value;
            } catch (java.lang.Exception e) {
                handleParseConversionException(e);
            }
        }

        private void eatText2(final java.lang.String value)
            throws org.xml.sax.SAXException
        {
            try {
                _ParentConceptCode = value;
            } catch (java.lang.Exception e) {
                handleParseConversionException(e);
            }
        }

        private void eatText3(final java.lang.String value)
            throws org.xml.sax.SAXException
        {
            try {
                _EvsTerminology = value;
            } catch (java.lang.Exception e) {
                handleParseConversionException(e);
            }
        }

        private void eatText4(final java.lang.String value)
            throws org.xml.sax.SAXException
        {
            try {
                _DepthOfHierarchyTree = javax.xml.bind.DatatypeConverter.parseInt(com.sun.xml.bind.WhiteSpaceProcessor.collapse(value));
                has_DepthOfHierarchyTree = true;
            } catch (java.lang.Exception e) {
                handleParseConversionException(e);
            }
        }

    }

}
