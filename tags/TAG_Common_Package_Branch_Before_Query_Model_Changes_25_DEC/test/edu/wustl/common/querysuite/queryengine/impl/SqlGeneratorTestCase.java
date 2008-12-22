/**
 * 
 */

package edu.wustl.common.querysuite.queryengine.impl;

import java.util.HashMap;
import java.util.HashSet;

import junit.framework.TestCase;
import edu.common.dynamicextensions.domain.Entity;
import edu.wustl.common.querysuite.EntityManagerMock;
import edu.wustl.common.querysuite.QueryGeneratorMock;
import edu.wustl.common.querysuite.factory.QueryObjectFactory;
import edu.wustl.common.querysuite.queryobject.IClass;
import edu.wustl.common.querysuite.queryobject.ICondition;
import edu.wustl.common.querysuite.queryobject.IConstraints;
import edu.wustl.common.querysuite.queryobject.IExpression;
import edu.wustl.common.querysuite.queryobject.IExpressionId;
import edu.wustl.common.querysuite.queryobject.IQuery;
import edu.wustl.common.querysuite.queryobject.IRule;
import edu.wustl.common.querysuite.queryobject.impl.JoinGraph;

/**
 * TestCase class for SqlGenerator.
 * @author prafull_kadam
 *
 */
public class SqlGeneratorTestCase extends TestCase
{

	SqlGenerator generator;

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	@Override
	protected void setUp() throws Exception
	{
		generator = new SqlGenerator(new EntityManagerMock());
		super.setUp();
	}

	/**
	 * To test the getSQL(ICondition condition) method.
	 *
	 */
	public void testParticpiantCondition()
	{
		IClass class1 = QueryGeneratorMock.createParticantClass();
		IExpression expression = QueryGeneratorMock.creatParticipantExpression1(class1);
		ICondition condition1;
		try
		{
			condition1 = QueryGeneratorMock.createParticipantCondition1(class1);
			assertEquals("Participant_0.ACTIVITY_STATUS='Active'", generator.getSQL(condition1,
					expression));

			condition1 = QueryGeneratorMock.createParticipantCondition2(class1);
			assertEquals("Participant_0.IDENTIFIER in (1,2,3,4)", generator.getSQL(condition1,
					expression));

			condition1 = QueryGeneratorMock.createParticipantCondition3(class1);
			assertEquals(
					"(Participant_0.BIRTH_DATE<='1-1-2000' And Participant_0.BIRTH_DATE>='1-2-2000')",
					generator.getSQL(condition1, expression));

			condition1 = QueryGeneratorMock.createParticipantCondition5(class1);
			assertEquals("Participant_0.ACTIVITY_STATUS like '%Active%'", generator.getSQL(
					condition1, expression));
		}
		catch (Exception e)
		{
			assertTrue("Unexpected Expection!!!", false);
		}
	}

	/**
	 * To test the getSQL(IRule rule) method.
	 *
	 */
	public void testParticpiantRule()
	{
		IClass class1 = QueryGeneratorMock.createParticantClass();
		IExpression expression = QueryGeneratorMock.creatParticipantExpression1(class1);
		IRule rule = QueryGeneratorMock.createParticipantRule1(class1);
		expression.addOperand(rule);
		try
		{
			assertEquals(
					generator.getSQL(rule),
					"Participant_0.IDENTIFIER in (1,2,3,4) And (Participant_0.BIRTH_DATE<='1-1-2000' And Participant_0.BIRTH_DATE>='1-2-2000')");
		}
		catch (Exception e)
		{
			assertTrue("Unexpected Expection!!!", false);
		}
	}

	/**
	 * To test the getSQL(IExpression, IJoinGraph, IExpression) method.
	 *
	 */
	public void testParticpiantExpression()
	{
		IClass class1 = QueryGeneratorMock.createParticantClass();
		IExpression expression = QueryGeneratorMock.creatParticipantExpression1(class1);

		try
		{
			String SQL = generator.getWherePartSQL(expression, null, false);
			//			System.out.println("testParticpiantExpression:"+ SQL);
			assertEquals(
					SQL,
					"(Participant_0.IDENTIFIER in (1,2,3,4) And (Participant_0.BIRTH_DATE<='1-1-2000' And Participant_0.BIRTH_DATE>='1-2-2000')) Or(Participant_0.ACTIVITY_STATUS='Active')");
			//			expression = QueryGeneratorMock.creatParticipantExpression2(class1);
			//			SQL = generator.getSQL(expression,null,null);
			//			System.out.println(SQL);
			//			assertEquals(SQL,"((Participant0.IDENTIFIER in (1,2,3,4) And (Participant0.BIRTH_DATE<='1-1-2000' And Participant0.BIRTH_DATE>='1-2-2000')) Or(Participant0.ACTIVITY_STATUS='Active')) And(Participant0.IDENTIFIER<10)");
		}
		catch (Exception e)
		{
			assertTrue("Unexpected Expection!!!", false);
		}
	}

	/**
	 * To test the getSQL(IExpression, IJoinGraph, IExpression) method.
	 *
	 */
	public void testParticpiantExpression1()
	{
		IQuery query = QueryGeneratorMock.creatParticipantQuery1();
		IConstraints constraints = query.getConstraints();
		IExpression rootExpression;
		try
		{
			rootExpression = constraints.getExpression(constraints.getRootExpressionId());
			generator.setJoinGraph((JoinGraph) constraints.getJoinGraph());

			generator.buildQuery(query);
			String SQL = generator.getWherePartSQL(rootExpression, null, false);
			//			System.out.println("*********"+SQL);
			assertEquals(
					"Incorrect SQL formed for the Root Expression !!!",
					"(Participant_1.ACTIVITY_STATUS='Active') And(ParticipantMedicalIdentif_2.MEDICAL_RECORD_NUMBER='M001')",
					SQL);

			//			String wherePart = generator.getWherePartSQL();
			//			//			System.out.println(wherePart);
			//			//			System.out.println("Where ParticipantMedicalIdentifier0.IDENTIFIER = ANY(Select ParticipantMedicalIdentifier0.PARTICIPANT_ID From catissue_part_medical_id ParticipantMedicalIdentifier0 where ParticipantMedicalIdentifier0.MEDICAL_RECORD_NUMBER='M001') And Participant0.IDENTIFIER = ANY(Select Participant0.IDENTIFIER From catissue_participant Participant0 where (Participant0.ACTIVITY_STATUS='Active') Or(Participant0.IDENTIFIER = ANY(Select ParticipantMedicalIdentifier0.PARTICIPANT_ID From catissue_part_medical_id ParticipantMedicalIdentifier0 where ParticipantMedicalIdentifier0.MEDICAL_RECORD_NUMBER='M001'))) ");
			//			assertEquals(
			//					"Incorrect SQL formed for Where clause of the Expression !!!",
			//					"Where Participant1.IDENTIFIER = ANY(Where (Participant1.ACTIVITY_STATUS='Active') And(ParticipantMedicalIdentif_2.MEDICAL_RECORD_NUMBER='M001')) And ParticipantMedicalIdentif_2.IDENTIFIER = ANY(ParticipantMedicalIdentif_2.MEDICAL_RECORD_NUMBER='M001') ",
			//					wherePart);

			String selectPart = generator.getSelectPart(rootExpression);
			//			System.out.println(selectPart);
			assertEquals(
					"Incorrect SQL formed for Select clause of the Expression !!!",
					"Select Participant_1.ACTIVITY_STATUS, Participant_1.BIRTH_DATE, Participant_1.DEATH_DATE, Participant_1.ETHNICITY, Participant_1.FIRST_NAME, Participant_1.GENDER, Participant_1.IDENTIFIER, Participant_1.LAST_NAME, Participant_1.MIDDLE_NAME, Participant_1.GENOTYPE, Participant_1.SOCIAL_SECURITY_NUMBER, Participant_1.VITAL_STATUS",
					selectPart);

			String fromPart = generator
					.getFromPartSQL(rootExpression, null, new HashSet<Integer>());
			//			System.out.println(fromPart);
			//			System.out.println("From catissue_participant Participant0 left join catissue_part_medical_id ParticipantMedicalIdentifier0 on (Participant0.IDENTIFIER=ParticipantMedicalIdentifier0.PARTICIPANT_ID)");
			assertEquals(
					"Incorrect SQL formed for From clause of the Expression !!!",
					"From catissue_participant Participant_1 left join catissue_part_medical_id ParticipantMedicalIdentif_2 on (Participant_1.IDENTIFIER=ParticipantMedicalIdentif_2.PARTICIPANT_ID)",
					fromPart);

		}
		catch (Exception e)
		{
			assertTrue("Unexpected Expection!!!", false);
		}
	}

	/**
	 * To test the generateSQL(IQuery) method for a Query: [Participant.activityStatus = 'Active'] AND [ParticipantMedicalIdentifier.medicalRecordNumber = 'M001'] 
	 *
	 */
	public void testParticipantQuery1()
	{
		IQuery query = QueryGeneratorMock.creatParticipantQuery1();
		try
		{
			String sql = generator.generateSQL(query);
			//			System.out.println("testParticipantQuery1:"+sql);

			assertEquals(
					"Incorrect SQL formed for From clause of the Expression !!!",
					"Select Participant_1.ACTIVITY_STATUS, Participant_1.BIRTH_DATE, Participant_1.DEATH_DATE, Participant_1.ETHNICITY, Participant_1.FIRST_NAME, Participant_1.GENDER, Participant_1.IDENTIFIER, Participant_1.LAST_NAME, Participant_1.MIDDLE_NAME, Participant_1.GENOTYPE, Participant_1.SOCIAL_SECURITY_NUMBER, Participant_1.VITAL_STATUS From catissue_participant Participant_1 left join catissue_part_medical_id ParticipantMedicalIdentif_2 on (Participant_1.IDENTIFIER=ParticipantMedicalIdentif_2.PARTICIPANT_ID) Where (Participant_1.ACTIVITY_STATUS='Active') And(ParticipantMedicalIdentif_2.MEDICAL_RECORD_NUMBER='M001')",
					sql);
		}
		catch (Exception e)
		{
			assertTrue("Unexpected Expection, While Generating SQL for the Query!!!", false);
		}
	}

	/**
	 * To test the generateSQL(IQuery) method for a Query: [Participant.activityStatus = 'Active'] 
	 *
	 */
	public void testParticipantQuery2()
	{
		IQuery query = QueryGeneratorMock.creatParticipantQuery2();
		try
		{
			String sql = generator.generateSQL(query);
			//			System.out.println("testParticipantQuery2:"+sql);
			assertEquals(
					"Incorrect SQL formed for From clause of the Expression !!!",
					"Select Participant_1.ACTIVITY_STATUS, Participant_1.BIRTH_DATE, Participant_1.DEATH_DATE, Participant_1.ETHNICITY, Participant_1.FIRST_NAME, Participant_1.GENDER, Participant_1.IDENTIFIER, Participant_1.LAST_NAME, Participant_1.MIDDLE_NAME, Participant_1.GENOTYPE, Participant_1.SOCIAL_SECURITY_NUMBER, Participant_1.VITAL_STATUS From catissue_participant Participant_1 Where Participant_1.ACTIVITY_STATUS='Active'",
					sql);
		}
		catch (Exception e)
		{
			assertTrue("Unexpected Expection, While Generating SQL for the Query!!!", false);
		}
	}

	public void testScgQuery1()
	{
		IQuery query = QueryGeneratorMock.createSCGQuery();
		try
		{
			String sql = generator.generateSQL(query);
			//			System.out.println("testScgQuery1:" + sql);
			assertEquals(
					"Incorrect SQL formed for From clause of the Expression !!!",
					"Select SpecimenCollectionGroup_1.IDENTIFIER, SpecimenCollectionGroup_1.NAME, SpecimenCollectionGroup_1.CLINICAL_DIAGNOSIS, SpecimenCollectionGroup_1.CLINICAL_STATUS, SpecimenCollectionGroup_1.ACTIVITY_STATUS From catissue_specimen_coll_group SpecimenCollectionGroup_1 left join catissue_specimen Specimen_2 on (SpecimenCollectionGroup_1.IDENTIFIER=Specimen_2.SPECIMEN_COLLECTION_GROUP_ID) left join catissue_specimen Specimen_3 on (Specimen_2.IDENTIFIER=Specimen_3.PARENT_SPECIMEN_ID) Where (SpecimenCollectionGroup_1.NAME like 'X%') Or(SpecimenCollectionGroup_1.IDENTIFIER = ANY(Select Specimen_2.SPECIMEN_COLLECTION_GROUP_ID From catissue_specimen  Specimen_2 left join catissue_specimen Specimen_3 on (Specimen_2.IDENTIFIER=Specimen_3.PARENT_SPECIMEN_ID) where (Specimen_2.TYPE='RNA') Or(Specimen_3.TYPE='DNA'))) And(SpecimenCollectionGroup_1.IDENTIFIER = ANY(Select Specimen_2.SPECIMEN_COLLECTION_GROUP_ID From catissue_specimen  Specimen_2 where Specimen_2.AVAILABLE=0)) Or(Specimen_2.TYPE!='DNA')",
					sql);
		}
		catch (Exception e)
		{
			assertTrue("Unexpected Expection, While Generating SQL for the Query!!!", false);
		}
	}

	/**
	 * 
	 * To Test the SQL for sample query no. 1 in the "SampleQueriesWithMultipleSubQueryApproach.doc".
	 * <pre>
	 *  P: LastNameStarts with 'S'
	 *  	C: ANY
	 *  		G: ANY
	 *  			S: Type equals "Fixed Tissue"
	 *  					OR
	 *  			S: Type equals "Fresh Tissue" 
	 * </pre>  	
	 */
	public void testSampleQuery1()
	{
		IQuery query = QueryGeneratorMock.createSampleQuery1();
		String sql;
		try
		{
			sql = generator.generateSQL(query);
			//			System.out.println("testSampleQuery1:"+sql);
			assertEquals(
					"Incorrect SQL formed for From clause of the Expression !!!",
					"Select Participant_1.ACTIVITY_STATUS, Participant_1.BIRTH_DATE, Participant_1.DEATH_DATE, Participant_1.ETHNICITY, Participant_1.FIRST_NAME, Participant_1.GENDER, Participant_1.IDENTIFIER, Participant_1.LAST_NAME, Participant_1.MIDDLE_NAME, Participant_1.GENOTYPE, Participant_1.SOCIAL_SECURITY_NUMBER, Participant_1.VITAL_STATUS From catissue_participant Participant_1 left join catissue_coll_prot_reg CollectionProtocolRegistr_2 on (Participant_1.IDENTIFIER=CollectionProtocolRegistr_2.PARTICIPANT_ID) left join catissue_specimen_coll_group SpecimenCollectionGroup_3 on (CollectionProtocolRegistr_2.IDENTIFIER=SpecimenCollectionGroup_3.COLLECTION_PROTOCOL_REG_ID) left join catissue_specimen Specimen_4 on (SpecimenCollectionGroup_3.IDENTIFIER=Specimen_4.SPECIMEN_COLLECTION_GROUP_ID) Where (Participant_1.FIRST_NAME like 's%') And((Specimen_4.TYPE='Fixed Tissue') Or(Specimen_4.TYPE='Fresh Tissue'))",
					sql);
		}
		catch (Exception e)
		{
			//e.printStackTrace();
			assertTrue("Unexpected Expection, While Generating SQL for the Query!!!", false);
		}
	}

	/**
	 * To test the SQL for the sample query no. 2 in the "SampleQueriesWithMultipleSubQueryApproach.doc"
	 * <pre>
	 *  P: ANY
	 *  	C: ANY
	 *  		G: Collection Site name equals "Site1" OR Collection Site name equals "Site2"
	 *  			S: Type equals "Fixed Tissue" OR Type equals "Fresh Tissue"
	 * </pre>
	 *  	
	 */
	public void testSampleQuery2()
	{
		IQuery query = QueryGeneratorMock.createSampleQuery2();
		String sql;
		try
		{
			sql = generator.generateSQL(query);
			//			System.out.println("testSampleQuery2:" + sql);
			assertEquals(
					"Incorrect SQL formed for From clause of the Expression !!!",
					"Select Participant_1.ACTIVITY_STATUS, Participant_1.BIRTH_DATE, Participant_1.DEATH_DATE, Participant_1.ETHNICITY, Participant_1.FIRST_NAME, Participant_1.GENDER, Participant_1.IDENTIFIER, Participant_1.LAST_NAME, Participant_1.MIDDLE_NAME, Participant_1.GENOTYPE, Participant_1.SOCIAL_SECURITY_NUMBER, Participant_1.VITAL_STATUS From catissue_participant Participant_1 left join catissue_coll_prot_reg CollectionProtocolRegistr_2 on (Participant_1.IDENTIFIER=CollectionProtocolRegistr_2.PARTICIPANT_ID) left join catissue_specimen_coll_group SpecimenCollectionGroup_3 on (CollectionProtocolRegistr_2.IDENTIFIER=SpecimenCollectionGroup_3.COLLECTION_PROTOCOL_REG_ID) left join catissue_site Site_4 on (SpecimenCollectionGroup_3.SITE_ID=Site_4.IDENTIFIER) left join catissue_specimen Specimen_5 on (SpecimenCollectionGroup_3.IDENTIFIER=Specimen_5.SPECIMEN_COLLECTION_GROUP_ID) Where ((Site_4.NAME='Site1') Or(Site_4.NAME='Site1')) And((Specimen_5.TYPE='Fixed Tissue') Or(Specimen_5.TYPE='Fresh Tissue'))",
					sql);
		}
		catch (Exception e)
		{
			//e.printStackTrace();
			assertTrue("Unexpected Expection, While Generating SQL for the Query!!!", false);
		}
	}

	/**
	 * 
	 * To test the SQL for sample query no. 3 in the "SampleQueriesWithMultipleSubQueryApproach.doc"
	 * <pre>
	 *  P: ANY
	 *  	C: ANY
	 *  		G: Clinical status equals "New Diagnosis"
	 *  			S: Type equals "DNA"
	 *  			OR
	 *  			S: Type equals "Fresh Tissue" 
	 *  		Pseudo AND
	 *  		G: Clinical status equals "Post Operative"
	 *  			S: Type equals "Fixed Tissue"
	 *  			OR
	 *  			S: Type equals "Fresh Tissue" 
	 *  </pre>
	 */
	public void testSampleQuery3()
	{
		IQuery query = QueryGeneratorMock.createSampleQuery3();
		String sql;
		try
		{
			sql = generator.generateSQL(query);
			//			System.out.println("testSampleQuery3:"+sql);
			assertEquals(
					"Incorrect SQL formed for From clause of the Expression !!!",
					"Select Participant_1.ACTIVITY_STATUS, Participant_1.BIRTH_DATE, Participant_1.DEATH_DATE, Participant_1.ETHNICITY, Participant_1.FIRST_NAME, Participant_1.GENDER, Participant_1.IDENTIFIER, Participant_1.LAST_NAME, Participant_1.MIDDLE_NAME, Participant_1.GENOTYPE, Participant_1.SOCIAL_SECURITY_NUMBER, Participant_1.VITAL_STATUS From catissue_participant Participant_1 left join catissue_coll_prot_reg CollectionProtocolRegistr_2 on (Participant_1.IDENTIFIER=CollectionProtocolRegistr_2.PARTICIPANT_ID) left join catissue_specimen_coll_group SpecimenCollectionGroup_3 on (CollectionProtocolRegistr_2.IDENTIFIER=SpecimenCollectionGroup_3.COLLECTION_PROTOCOL_REG_ID) left join catissue_specimen Specimen_4 on (SpecimenCollectionGroup_3.IDENTIFIER=Specimen_4.SPECIMEN_COLLECTION_GROUP_ID) Where (CollectionProtocolRegistr_2.IDENTIFIER = ANY(Select SpecimenCollectionGroup_3.COLLECTION_PROTOCOL_REG_ID From catissue_specimen_coll_group  SpecimenCollectionGroup_3 left join catissue_specimen Specimen_4 on (SpecimenCollectionGroup_3.IDENTIFIER=Specimen_4.SPECIMEN_COLLECTION_GROUP_ID) where (SpecimenCollectionGroup_3.CLINICAL_DIAGNOSIS='New Diagnosis') And((Specimen_4.TYPE='DNA') Or(Specimen_4.TYPE='Fresh Tissue')))) And(CollectionProtocolRegistr_2.IDENTIFIER = ANY(Select SpecimenCollectionGroup_3.COLLECTION_PROTOCOL_REG_ID From catissue_specimen_coll_group  SpecimenCollectionGroup_3 left join catissue_specimen Specimen_4 on (SpecimenCollectionGroup_3.IDENTIFIER=Specimen_4.SPECIMEN_COLLECTION_GROUP_ID) where (SpecimenCollectionGroup_3.CLINICAL_DIAGNOSIS='Post-Operative') And((Specimen_4.TYPE='Fixed Tissue') Or(Specimen_4.TYPE='Fixed Tissue'))))",
					sql);
		}
		catch (Exception e)
		{
			//			e.printStackTrace();
			assertTrue("Unexpected Expection, While Generating SQL for the Query!!!", false);
		}
	}

	/**
	 * 
	 * To test the SQL for the sample query no. 4 in the "SampleQueriesWithMultipleSubQueryApproach.doc"
	 * <pre>
	 * The Query is as follows:
	 *  P: ANY
	 *  	C: ANY
	 *  		G: Clinical status equals "New Diagnosis"
	 *  			S: Type equals "DNA"
	 *  			Pseudo AND
	 *  			S: Type equals "Milk" 
	 *  		Pseudo AND
	 *  		G: Clinical status equals "Post Operative"
	 *  			S: Type equals "Fixed Tissue"
	 *  			OR
	 *  			S: Type equals "Fresh Tissue" 
	 *  </pre>	
	 */
	public void testSampleQuery4()
	{
		IQuery query = QueryGeneratorMock.createSampleQuery4();
		String sql;
		try
		{
			sql = generator.generateSQL(query);
			//			System.out.println("testSampleQuery4:" + sql);
			assertEquals(
					"Incorrect SQL formed for From clause of the Expression !!!",
					"Select Participant_1.ACTIVITY_STATUS, Participant_1.BIRTH_DATE, Participant_1.DEATH_DATE, Participant_1.ETHNICITY, Participant_1.FIRST_NAME, Participant_1.GENDER, Participant_1.IDENTIFIER, Participant_1.LAST_NAME, Participant_1.MIDDLE_NAME, Participant_1.GENOTYPE, Participant_1.SOCIAL_SECURITY_NUMBER, Participant_1.VITAL_STATUS From catissue_participant Participant_1 left join catissue_coll_prot_reg CollectionProtocolRegistr_2 on (Participant_1.IDENTIFIER=CollectionProtocolRegistr_2.PARTICIPANT_ID) left join catissue_specimen_coll_group SpecimenCollectionGroup_3 on (CollectionProtocolRegistr_2.IDENTIFIER=SpecimenCollectionGroup_3.COLLECTION_PROTOCOL_REG_ID) left join catissue_specimen Specimen_4 on (SpecimenCollectionGroup_3.IDENTIFIER=Specimen_4.SPECIMEN_COLLECTION_GROUP_ID) Where (CollectionProtocolRegistr_2.IDENTIFIER = ANY(Select SpecimenCollectionGroup_3.COLLECTION_PROTOCOL_REG_ID From catissue_specimen_coll_group  SpecimenCollectionGroup_3 left join catissue_specimen Specimen_4 on (SpecimenCollectionGroup_3.IDENTIFIER=Specimen_4.SPECIMEN_COLLECTION_GROUP_ID) where (SpecimenCollectionGroup_3.CLINICAL_STATUS='New Diagnosis') And(SpecimenCollectionGroup_3.IDENTIFIER = ANY(Select Specimen_4.SPECIMEN_COLLECTION_GROUP_ID From catissue_specimen  Specimen_4 where Specimen_4.TYPE='DNA')) And(SpecimenCollectionGroup_3.IDENTIFIER = ANY(Select Specimen_4.SPECIMEN_COLLECTION_GROUP_ID From catissue_specimen  Specimen_4 where Specimen_4.TYPE='Milk')))) And(CollectionProtocolRegistr_2.IDENTIFIER = ANY(Select SpecimenCollectionGroup_3.COLLECTION_PROTOCOL_REG_ID From catissue_specimen_coll_group  SpecimenCollectionGroup_3 left join catissue_specimen Specimen_4 on (SpecimenCollectionGroup_3.IDENTIFIER=Specimen_4.SPECIMEN_COLLECTION_GROUP_ID) where (SpecimenCollectionGroup_3.CLINICAL_STATUS='Post Operative') And(Specimen_4.TYPE='Fixed Tissue') Or(Specimen_4.TYPE='Fresh Tissue')))",
					sql);
		}
		catch (Exception e)
		{
			//			e.printStackTrace();
			assertTrue("Unexpected Expection, While Generating SQL for the Query!!!", false);
		}
	}

	/**
	 * 
	 * To test the SQL for the sample query no. 5 in the "SampleQueriesWithMultipleSubQueryApproach.doc"
	 * <pre>
	 * The Query is as follows:
	 *  P: ANY
	 *  	C: ANY
	 *  		G: ANY
	 *  			S: Type equals "Fixed Tissue"
	 *	  				S: Type equals "Milk" 
	 *  </pre>	
	 */
	public void testSampleQuery5()
	{
		IQuery query = QueryGeneratorMock.createSampleQuery5();
		String sql;
		try
		{
			sql = generator.generateSQL(query);
			//			System.out.println("testSampleQuery5:" + sql);
			assertEquals(
					"Incorrect SQL formed for From clause of the Expression !!!",
					"Select Participant_1.ACTIVITY_STATUS, Participant_1.BIRTH_DATE, Participant_1.DEATH_DATE, Participant_1.ETHNICITY, Participant_1.FIRST_NAME, Participant_1.GENDER, Participant_1.IDENTIFIER, Participant_1.LAST_NAME, Participant_1.MIDDLE_NAME, Participant_1.GENOTYPE, Participant_1.SOCIAL_SECURITY_NUMBER, Participant_1.VITAL_STATUS From catissue_participant Participant_1 left join catissue_coll_prot_reg CollectionProtocolRegistr_2 on (Participant_1.IDENTIFIER=CollectionProtocolRegistr_2.PARTICIPANT_ID) left join catissue_specimen_coll_group SpecimenCollectionGroup_3 on (CollectionProtocolRegistr_2.IDENTIFIER=SpecimenCollectionGroup_3.COLLECTION_PROTOCOL_REG_ID) left join catissue_specimen Specimen_4 on (SpecimenCollectionGroup_3.IDENTIFIER=Specimen_4.SPECIMEN_COLLECTION_GROUP_ID) left join catissue_specimen Specimen_5 on (Specimen_4.IDENTIFIER=Specimen_5.PARENT_SPECIMEN_ID) Where (Specimen_4.TYPE='Fixed Tissue') And(Specimen_5.TYPE='Milk')",
					sql);
		}
		catch (Exception e)
		{
			//			e.printStackTrace();
			assertTrue("Unexpected Expection, While Generating SQL for the Query!!!", false);
		}
	}

	/**
	 * 
	 * To test the SQL for the sample query no. 6 in the "SampleQueriesWithMultipleSubQueryApproach.doc"
	 * TODO add Specimen Event parameter part in this Query.
	 * <pre>
	 *  P: ANY
	 *  	C: ANY
	 *  		G: ANY
	 *  			S: Type equals "Fixed Tissue" 
	 *  				S: Type equals "Amniotic Fluid"
	 * </pre> 	
	 */
	public void testSampleQuery6()
	{
		IQuery query = QueryGeneratorMock.createSampleQuery6();
		String sql;
		try
		{
			sql = generator.generateSQL(query);
			//			System.out.println("testSampleQuery6:"+sql);
			assertEquals(
					"Incorrect SQL formed for From clause of the Expression !!!",
					"Select Participant_1.ACTIVITY_STATUS, Participant_1.BIRTH_DATE, Participant_1.DEATH_DATE, Participant_1.ETHNICITY, Participant_1.FIRST_NAME, Participant_1.GENDER, Participant_1.IDENTIFIER, Participant_1.LAST_NAME, Participant_1.MIDDLE_NAME, Participant_1.GENOTYPE, Participant_1.SOCIAL_SECURITY_NUMBER, Participant_1.VITAL_STATUS From catissue_participant Participant_1 left join catissue_coll_prot_reg CollectionProtocolRegistr_2 on (Participant_1.IDENTIFIER=CollectionProtocolRegistr_2.PARTICIPANT_ID) left join catissue_specimen_coll_group SpecimenCollectionGroup_3 on (CollectionProtocolRegistr_2.IDENTIFIER=SpecimenCollectionGroup_3.COLLECTION_PROTOCOL_REG_ID) left join catissue_specimen Specimen_4 on (SpecimenCollectionGroup_3.IDENTIFIER=Specimen_4.SPECIMEN_COLLECTION_GROUP_ID) left join catissue_specimen Specimen_5 on (Specimen_4.IDENTIFIER=Specimen_5.PARENT_SPECIMEN_ID) Where (Specimen_4.TYPE='Fixed Tissue') And(Specimen_5.TYPE='Amniotic Fluid')",
					sql);
		}
		catch (Exception e)
		{
			//e.printStackTrace();
			assertTrue("Unexpected Expection, While Generating SQL for the Query!!!", false);
		}
	}

	/**
	 * To test the method get alias Name.
	 * - The length of alias returned from this method should be less than 30. 
	 * - The aliases for two different entities in two different Expression with different AliasAppender should not be same. 
	 */
	public void testGetAliasName1()
	{
		generator.aliasNameMap = new HashMap<String, String>();
		generator.aliasAppenderMap = new HashMap<IExpressionId, Integer>();
		EntityManagerMock enitytManager = new EntityManagerMock();
		IConstraints constraints = QueryObjectFactory.createConstraints();
		try
		{
			// Creating entity with name "edu.wustl.catissuecore.domain.CollectionProtocolRegistration"
			Entity entityOne = (Entity) enitytManager
					.getEntityByName(EntityManagerMock.COLLECTION_PROTOCOL_REGISTRATION_NAME);
			IClass collectionProtocolRegClass = QueryObjectFactory.createClass(enitytManager
					.getEntityByName(EntityManagerMock.COLLECTION_PROTOCOL_REGISTRATION_NAME));
			IExpression expressionOne = constraints.addExpression(collectionProtocolRegClass);
			generator.aliasAppenderMap.put(expressionOne.getExpressionId(), new Integer(1));
			String aliasNameExpected1 = generator.getAliasName(entityOne, expressionOne);
			//			System.out.println(aliasNameExpected1);
			assertTrue("The length of alias exceeds 30 characters!!!",
					aliasNameExpected1.length() <= 30);
			assertEquals("Incorrect AliasName returned from getAliasName method!!!",
					"CollectionProtocolRegistr_1", aliasNameExpected1);

			// Creating another entity with name "edu.wustl.catissuecore.domain.CollectionProtocolRegistration"
			// because of different ExpressionId the alias will different.
			Entity entityTwo = (Entity) enitytManager
					.getEntityByName(EntityManagerMock.COLLECTION_PROTOCOL_REGISTRATION_NAME);
			IExpression expressionTwo = constraints.addExpression(collectionProtocolRegClass);
			generator.aliasAppenderMap.put(expressionTwo.getExpressionId(), new Integer(2));
			String aliasNameExpected2 = generator.getAliasName(entityTwo, expressionTwo);
			//			System.out.println(aliasNameExpected2);
			assertNotSame(
					"getAliasName method returned same alias name for the classes having same ClassName but are different Alias Appender!!!",
					aliasNameExpected1, aliasNameExpected2);
			assertTrue("The length of alias exceeds 30 characters!!!",
					aliasNameExpected2.length() <= 30);
			assertEquals("Incorrect AliasName returned from getAliasName method!!!",
					"CollectionProtocolRegistr_2", aliasNameExpected2);

		}
		catch (Exception e)
		{
			//			e.printStackTrace();
			assertTrue("Unexpected Expection!!!", false);
		}
	}

	/**
	 * To test the method get alias Name, for case when two classes with same name have different packages. 
	 * - aliasName for two such classes should be different.
	 * - lenght of the two aliasNames should be less than 30.
	 */
	public void testGetAliasName2()
	{
		generator.aliasNameMap = new HashMap<String, String>();
		generator.aliasAppenderMap = new HashMap<IExpressionId, Integer>();
		EntityManagerMock enitytManager = new EntityManagerMock();
		IConstraints constraints = QueryObjectFactory.createConstraints();
		try
		{
			// Creating entity with name "edu.wustl.catissuecore.domain.CollectionProtocolRegistration"
			Entity entityOne = (Entity) enitytManager
					.getEntityByName(EntityManagerMock.COLLECTION_PROTOCOL_REGISTRATION_NAME);
			IClass collectionProtocolRegClass = QueryObjectFactory.createClass(enitytManager
					.getEntityByName(EntityManagerMock.COLLECTION_PROTOCOL_REGISTRATION_NAME));
			IExpression expressionOne = constraints.addExpression(collectionProtocolRegClass);
			generator.aliasAppenderMap.put(expressionOne.getExpressionId(), new Integer(1));
			String aliasNameExpected1 = generator.getAliasName(entityOne, expressionOne);
			//			System.out.println(aliasNameExpected1);
			assertTrue("The length of alias exceeds 30 characters!!!",
					aliasNameExpected1.length() <= 30);
			assertEquals("Incorrect AliasName returned from getAliasName method!!!",
					"CollectionProtocolRegistr_1", aliasNameExpected1);

			// Creating another entity with name "CAedu.wustl.catissuecore.domain.CollectionProtocolRegistration"
			// in this entity class "CollectionProtocolRegistration" will have different package Name, so the alias will not be same as that of above.
			Entity entityThree = (Entity) enitytManager
					.getEntityByName(EntityManagerMock.COLLECTION_PROTOCOL_REGISTRATION_NAME);
			entityThree.setName("CA" + entityThree.getName());
			IExpression expressionThree = constraints.addExpression(collectionProtocolRegClass);
			generator.aliasAppenderMap.put(expressionThree.getExpressionId(), new Integer(1));
			String aliasNameExpected2 = generator.getAliasName(entityThree, expressionThree);
			//			System.out.println(aliasNameExpected2);
			assertNotSame(
					"getAliasName method returned same alias name for the classes having same ClassName but are different packages!!!",
					aliasNameExpected1, aliasNameExpected2);
			assertTrue("The length of alias exceeds 30 characters!!!",
					aliasNameExpected2.length() <= 30);
			assertEquals("Incorrect AliasName returned from getAliasName method!!!",
					"CollectionProtocolRegistr1_1", aliasNameExpected2);

		}
		catch (Exception e)
		{
			//			e.printStackTrace();
			assertTrue("Unexpected Expection!!!", false);
		}
	}
}