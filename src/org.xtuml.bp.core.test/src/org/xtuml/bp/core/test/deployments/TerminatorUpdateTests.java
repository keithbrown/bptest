package org.xtuml.bp.core.test.deployments;

import java.io.File;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.xtuml.bp.core.DataType_c;
import org.xtuml.bp.core.Deployment_c;
import org.xtuml.bp.core.Ooaofooa;
import org.xtuml.bp.core.TerminatorServiceParameter_c;
import org.xtuml.bp.core.TerminatorService_c;
import org.xtuml.bp.core.Terminator_c;
import org.xtuml.bp.core.ui.DeleteStaleServicesOnD_TERMAction;
import org.xtuml.bp.core.ui.Selection;
import org.xtuml.bp.test.common.BaseTest;
import org.xtuml.bp.test.common.OrderedRunner;

@RunWith(OrderedRunner.class)
public class TerminatorUpdateTests extends BaseTest {

    private static final String[] PROJECT_NAMES = { "DeploymentsTests" };
    private static final String BASE_INT_FILE = "/DeploymentsDomains/masl/DeploymentsDomain1/DeploymentsDomain1.int";
    private static final String INT_FILE_TEMPLATE = "/DeploymentsDomains/masl/DeploymentsDomain1/DeploymentsDomain1_update%d.int";

    @Before
    public void setUp() throws Exception {
        super.setUp();
        for (String project : PROJECT_NAMES) {
            loadProject(project);
        }
        modelRoot = Ooaofooa
                .getInstance("/DeploymentsTests/models/DeploymentsTests/DeploymentsTests/DeploymentsTests.xtuml");
        m_sys = modelRoot.getRoot();
    }
    
    /*
     * Change return type
     */
    @Test
    public void testUpdate1() throws Exception {

        // import the terminator and do the basic test
        Deployment_c deployment = basicTest();

        // check that the file exists
        File testFile = new File(getTestModelRespositoryLocation() + String.format(INT_FILE_TEMPLATE, 1));
        assertTrue("Cannot access test file.", testFile.exists());

        // trigger the import action on updated file
        Selection.getInstance().clear();
        Selection.getInstance().addToSelection(deployment);
        ImportTerminatorsFromFileOnD_DEPLAction action = new ImportTerminatorsFromFileOnD_DEPLAction(
                Arrays.asList(new String[] { getTestModelRespositoryLocation() + String.format(INT_FILE_TEMPLATE, 1) })
                        .iterator());
        action.run(null);

        // check the resulting instances
        Terminator_c providedTerm = Terminator_c.getOneD_TERMOnR1650(deployment,
                (selected) -> "DeploymentsDomain1".equals(((Terminator_c) selected).getName()));
        assertTrue("Provided terminator missing.", null != providedTerm && providedTerm.getProvider());
        TerminatorService_c provSvc = TerminatorService_c.getOneD_TSVCOnR1651(providedTerm,
                (selected) -> "public_service1".equals(((TerminatorService_c) selected).getName()));
        assertNotNull("Provided terminator service missing.", provSvc);
        TerminatorServiceParameter_c provSvcParam = TerminatorServiceParameter_c.getOneD_TSPARMOnR1652(provSvc,
                (selected) -> "real_param".equals(((TerminatorServiceParameter_c) selected).getName()));
        assertNotNull("Provided terminator service parameter missing.", provSvcParam);
        DataType_c s_dt = DataType_c.getOneS_DTOnR1653(provSvcParam);
        assertTrue("Incorrect parameter type.", null != s_dt && "real".equals(s_dt.getName()));
        Terminator_c requiredTerm = Terminator_c.getOneD_TERMOnR1650(deployment,
                (selected) -> "DeploymentsDomain1::term1".equals(((Terminator_c) selected).getName()));
        assertTrue("Required terminator missing.", null != requiredTerm && !requiredTerm.getProvider());
        TerminatorService_c reqSvc = TerminatorService_c.getOneD_TSVCOnR1651(requiredTerm,
                (selected) -> "term_service1".equals(((TerminatorService_c) selected).getName()));
        assertNotNull("Required terminator service missing.", reqSvc);
        s_dt = DataType_c.getOneS_DTOnR1656(reqSvc);
        assertTrue("Incorrect return type.", null != s_dt && "integer".equals(s_dt.getName()));
        TerminatorServiceParameter_c reqSvcParam = TerminatorServiceParameter_c.getOneD_TSPARMOnR1652(reqSvc,
                (selected) -> "enum_param".equals(((TerminatorServiceParameter_c) selected).getName()));
        assertNotNull("Required terminator service parameter missing.", reqSvcParam);
        s_dt = DataType_c.getOneS_DTOnR1653(reqSvcParam);
        assertTrue("Incorrect parameter type.", null != s_dt && "DeploymentsDomain1::MyEnum".equals(s_dt.getName()));

    }

    /*
     * Change parameter type
     */
    @Test
    public void testUpdate2() throws Exception {

        // import the terminator and do the basic test
        Deployment_c deployment = basicTest();

        // check that the file exists
        File testFile = new File(getTestModelRespositoryLocation() + String.format(INT_FILE_TEMPLATE, 2));
        assertTrue("Cannot access test file.", testFile.exists());

        // trigger the import action on updated file
        Selection.getInstance().clear();
        Selection.getInstance().addToSelection(deployment);
        ImportTerminatorsFromFileOnD_DEPLAction action = new ImportTerminatorsFromFileOnD_DEPLAction(
                Arrays.asList(new String[] { getTestModelRespositoryLocation() + String.format(INT_FILE_TEMPLATE, 2) })
                        .iterator());
        action.run(null);

        // check the resulting instances
        Terminator_c providedTerm = Terminator_c.getOneD_TERMOnR1650(deployment,
                (selected) -> "DeploymentsDomain1".equals(((Terminator_c) selected).getName()));
        assertTrue("Provided terminator missing.", null != providedTerm && providedTerm.getProvider());
        TerminatorService_c provSvc = TerminatorService_c.getOneD_TSVCOnR1651(providedTerm,
                (selected) -> "public_service1".equals(((TerminatorService_c) selected).getName()));
        assertNotNull("Provided terminator service missing.", provSvc);
        TerminatorServiceParameter_c provSvcParam = TerminatorServiceParameter_c.getOneD_TSPARMOnR1652(provSvc,
                (selected) -> "real_param".equals(((TerminatorServiceParameter_c) selected).getName()));
        assertNotNull("Provided terminator service parameter missing.", provSvcParam);
        DataType_c s_dt = DataType_c.getOneS_DTOnR1653(provSvcParam);
        assertTrue("Incorrect parameter type.", null != s_dt && "real".equals(s_dt.getName()));
        Terminator_c requiredTerm = Terminator_c.getOneD_TERMOnR1650(deployment,
                (selected) -> "DeploymentsDomain1::term1".equals(((Terminator_c) selected).getName()));
        assertTrue("Required terminator missing.", null != requiredTerm && !requiredTerm.getProvider());
        TerminatorService_c reqSvc = TerminatorService_c.getOneD_TSVCOnR1651(requiredTerm,
                (selected) -> "term_service1".equals(((TerminatorService_c) selected).getName()));
        assertNotNull("Required terminator service missing.", reqSvc);
        s_dt = DataType_c.getOneS_DTOnR1656(reqSvc);
        assertTrue("Incorrect return type.", null != s_dt && "void".equals(s_dt.getName()));
        TerminatorServiceParameter_c reqSvcParam = TerminatorServiceParameter_c.getOneD_TSPARMOnR1652(reqSvc,
                (selected) -> "enum_param".equals(((TerminatorServiceParameter_c) selected).getName()));
        assertNotNull("Required terminator service parameter missing.", reqSvcParam);
        s_dt = DataType_c.getOneS_DTOnR1653(reqSvcParam);
        assertTrue("Incorrect parameter type.", null != s_dt && "string".equals(s_dt.getName()));

    }

    /*
     * Change parameter name
     */
    @Test
    public void testUpdate3() throws Exception {

        // import the terminator and do the basic test
        Deployment_c deployment = basicTest();

        // check that the file exists
        File testFile = new File(getTestModelRespositoryLocation() + String.format(INT_FILE_TEMPLATE, 3));
        assertTrue("Cannot access test file.", testFile.exists());

        // trigger the import action on updated file
        Selection.getInstance().clear();
        Selection.getInstance().addToSelection(deployment);
        ImportTerminatorsFromFileOnD_DEPLAction action = new ImportTerminatorsFromFileOnD_DEPLAction(
                Arrays.asList(new String[] { getTestModelRespositoryLocation() + String.format(INT_FILE_TEMPLATE, 3) })
                        .iterator());
        action.run(null);

        // check the resulting instances
        Terminator_c providedTerm = Terminator_c.getOneD_TERMOnR1650(deployment,
                (selected) -> "DeploymentsDomain1".equals(((Terminator_c) selected).getName()));
        assertTrue("Provided terminator missing.", null != providedTerm && providedTerm.getProvider());
        TerminatorService_c provSvc = TerminatorService_c.getOneD_TSVCOnR1651(providedTerm,
                (selected) -> "public_service1".equals(((TerminatorService_c) selected).getName()));
        assertNotNull("Provided terminator service missing.", provSvc);
        TerminatorServiceParameter_c provSvcParam = TerminatorServiceParameter_c.getOneD_TSPARMOnR1652(provSvc,
                (selected) -> "real_param".equals(((TerminatorServiceParameter_c) selected).getName()));
        assertNotNull("Provided terminator service parameter missing.", provSvcParam);
        DataType_c s_dt = DataType_c.getOneS_DTOnR1653(provSvcParam);
        assertTrue("Incorrect parameter type.", null != s_dt && "real".equals(s_dt.getName()));
        Terminator_c requiredTerm = Terminator_c.getOneD_TERMOnR1650(deployment,
                (selected) -> "DeploymentsDomain1::term1".equals(((Terminator_c) selected).getName()));
        assertTrue("Required terminator missing.", null != requiredTerm && !requiredTerm.getProvider());
        TerminatorService_c reqSvc = TerminatorService_c.getOneD_TSVCOnR1651(requiredTerm,
                (selected) -> "term_service1".equals(((TerminatorService_c) selected).getName()));
        assertNotNull("Required terminator service missing.", reqSvc);
        s_dt = DataType_c.getOneS_DTOnR1656(reqSvc);
        assertTrue("Incorrect return type.", null != s_dt && "void".equals(s_dt.getName()));
        TerminatorServiceParameter_c reqSvcParam = TerminatorServiceParameter_c.getOneD_TSPARMOnR1652(reqSvc,
                (selected) -> "enum_param2".equals(((TerminatorServiceParameter_c) selected).getName()));
        assertNotNull("Required terminator service parameter missing.", reqSvcParam);
        s_dt = DataType_c.getOneS_DTOnR1653(reqSvcParam);
        assertTrue("Incorrect parameter type.", null != s_dt && "DeploymentsDomain1::MyEnum".equals(s_dt.getName()));

    }

    /*
     * Add parameter
     */
    @Test
    public void testUpdate4() throws Exception {

        // import the terminator and do the basic test
        Deployment_c deployment = basicTest();

        // check that the file exists
        File testFile = new File(getTestModelRespositoryLocation() + String.format(INT_FILE_TEMPLATE, 4));
        assertTrue("Cannot access test file.", testFile.exists());

        // trigger the import action on updated file
        Selection.getInstance().clear();
        Selection.getInstance().addToSelection(deployment);
        ImportTerminatorsFromFileOnD_DEPLAction action = new ImportTerminatorsFromFileOnD_DEPLAction(
                Arrays.asList(new String[] { getTestModelRespositoryLocation() + String.format(INT_FILE_TEMPLATE, 4) })
                        .iterator());
        action.run(null);

        // check the resulting instances
        Terminator_c providedTerm = Terminator_c.getOneD_TERMOnR1650(deployment,
                (selected) -> "DeploymentsDomain1".equals(((Terminator_c) selected).getName()));
        assertTrue("Provided terminator missing.", null != providedTerm && providedTerm.getProvider());
        TerminatorService_c provSvc = TerminatorService_c.getOneD_TSVCOnR1651(providedTerm,
                (selected) -> "public_service1".equals(((TerminatorService_c) selected).getName()));
        assertNotNull("Provided terminator service missing.", provSvc);
        TerminatorServiceParameter_c provSvcParam = TerminatorServiceParameter_c.getOneD_TSPARMOnR1652(provSvc,
                (selected) -> "real_param".equals(((TerminatorServiceParameter_c) selected).getName()));
        assertNotNull("Provided terminator service parameter missing.", provSvcParam);
        DataType_c s_dt = DataType_c.getOneS_DTOnR1653(provSvcParam);
        assertTrue("Incorrect parameter type.", null != s_dt && "real".equals(s_dt.getName()));
        Terminator_c requiredTerm = Terminator_c.getOneD_TERMOnR1650(deployment,
                (selected) -> "DeploymentsDomain1::term1".equals(((Terminator_c) selected).getName()));
        assertTrue("Required terminator missing.", null != requiredTerm && !requiredTerm.getProvider());
        TerminatorService_c reqSvc = TerminatorService_c.getOneD_TSVCOnR1651(requiredTerm,
                (selected) -> "term_service1".equals(((TerminatorService_c) selected).getName()));
        assertNotNull("Required terminator service missing.", reqSvc);
        s_dt = DataType_c.getOneS_DTOnR1656(reqSvc);
        assertTrue("Incorrect return type.", null != s_dt && "void".equals(s_dt.getName()));
        TerminatorServiceParameter_c reqSvcParam = TerminatorServiceParameter_c.getOneD_TSPARMOnR1652(reqSvc,
                (selected) -> "enum_param".equals(((TerminatorServiceParameter_c) selected).getName()));
        assertNotNull("Required terminator service parameter missing.", reqSvcParam);
        s_dt = DataType_c.getOneS_DTOnR1653(reqSvcParam);
        assertTrue("Incorrect parameter type.", null != s_dt && "DeploymentsDomain1::MyEnum".equals(s_dt.getName()));
        TerminatorServiceParameter_c reqSvcParam2 = TerminatorServiceParameter_c.getOneD_TSPARMOnR1652(reqSvc,
                (selected) -> "other_param".equals(((TerminatorServiceParameter_c) selected).getName()));
        assertNotNull("Required terminator service parameter missing.", reqSvcParam2);
        s_dt = DataType_c.getOneS_DTOnR1653(reqSvcParam2);
        assertTrue("Incorrect parameter type.", null != s_dt && "string".equals(s_dt.getName()));

    }

    /*
     * Remove parameter
     */
    @Test
    public void testUpdate5() throws Exception {

        // import the terminator and do the basic test
        Deployment_c deployment = basicTest();

        // check that the file exists
        File testFile = new File(getTestModelRespositoryLocation() + String.format(INT_FILE_TEMPLATE, 5));
        assertTrue("Cannot access test file.", testFile.exists());

        // trigger the import action on updated file
        Selection.getInstance().clear();
        Selection.getInstance().addToSelection(deployment);
        ImportTerminatorsFromFileOnD_DEPLAction action = new ImportTerminatorsFromFileOnD_DEPLAction(
                Arrays.asList(new String[] { getTestModelRespositoryLocation() + String.format(INT_FILE_TEMPLATE, 5) })
                        .iterator());
        action.run(null);

        // check the resulting instances
        Terminator_c providedTerm = Terminator_c.getOneD_TERMOnR1650(deployment,
                (selected) -> "DeploymentsDomain1".equals(((Terminator_c) selected).getName()));
        assertTrue("Provided terminator missing.", null != providedTerm && providedTerm.getProvider());
        TerminatorService_c provSvc = TerminatorService_c.getOneD_TSVCOnR1651(providedTerm,
                (selected) -> "public_service1".equals(((TerminatorService_c) selected).getName()));
        assertNotNull("Provided terminator service missing.", provSvc);
        TerminatorServiceParameter_c provSvcParam = TerminatorServiceParameter_c.getOneD_TSPARMOnR1652(provSvc,
                (selected) -> "real_param".equals(((TerminatorServiceParameter_c) selected).getName()));
        assertNotNull("Provided terminator service parameter missing.", provSvcParam);
        DataType_c s_dt = DataType_c.getOneS_DTOnR1653(provSvcParam);
        assertTrue("Incorrect parameter type.", null != s_dt && "real".equals(s_dt.getName()));
        Terminator_c requiredTerm = Terminator_c.getOneD_TERMOnR1650(deployment,
                (selected) -> "DeploymentsDomain1::term1".equals(((Terminator_c) selected).getName()));
        assertTrue("Required terminator missing.", null != requiredTerm && !requiredTerm.getProvider());
        TerminatorService_c reqSvc = TerminatorService_c.getOneD_TSVCOnR1651(requiredTerm,
                (selected) -> "term_service1".equals(((TerminatorService_c) selected).getName()));
        assertNotNull("Required terminator service missing.", reqSvc);
        s_dt = DataType_c.getOneS_DTOnR1656(reqSvc);
        assertTrue("Incorrect return type.", null != s_dt && "void".equals(s_dt.getName()));
        TerminatorServiceParameter_c reqSvcParam = TerminatorServiceParameter_c.getOneD_TSPARMOnR1652(reqSvc,
                (selected) -> "enum_param".equals(((TerminatorServiceParameter_c) selected).getName()));
        assertNull("Required terminator service parameter not removed.", reqSvcParam);

    }

    /*
     * Change service name
     */
    @Test
    public void testUpdate6() throws Exception {

        // import the terminator and do the basic test
        Deployment_c deployment = basicTest();

        // check that the file exists
        File testFile = new File(getTestModelRespositoryLocation() + String.format(INT_FILE_TEMPLATE, 6));
        assertTrue("Cannot access test file.", testFile.exists());

        // trigger the import action on updated file
        Selection.getInstance().clear();
        Selection.getInstance().addToSelection(deployment);
        ImportTerminatorsFromFileOnD_DEPLAction action = new ImportTerminatorsFromFileOnD_DEPLAction(
                Arrays.asList(new String[] { getTestModelRespositoryLocation() + String.format(INT_FILE_TEMPLATE, 6) })
                        .iterator());
        action.run(null);

        // check the resulting instances
        Terminator_c providedTerm = Terminator_c.getOneD_TERMOnR1650(deployment,
                (selected) -> "DeploymentsDomain1".equals(((Terminator_c) selected).getName()));
        assertTrue("Provided terminator missing.", null != providedTerm && providedTerm.getProvider());
        TerminatorService_c provSvc = TerminatorService_c.getOneD_TSVCOnR1651(providedTerm,
                (selected) -> "public_service1".equals(((TerminatorService_c) selected).getName()));
        assertNotNull("Provided terminator service missing.", provSvc);
        TerminatorServiceParameter_c provSvcParam = TerminatorServiceParameter_c.getOneD_TSPARMOnR1652(provSvc,
                (selected) -> "real_param".equals(((TerminatorServiceParameter_c) selected).getName()));
        assertNotNull("Provided terminator service parameter missing.", provSvcParam);
        DataType_c s_dt = DataType_c.getOneS_DTOnR1653(provSvcParam);
        assertTrue("Incorrect parameter type.", null != s_dt && "real".equals(s_dt.getName()));
        Terminator_c requiredTerm = Terminator_c.getOneD_TERMOnR1650(deployment,
                (selected) -> "DeploymentsDomain1::term1".equals(((Terminator_c) selected).getName()));
        assertTrue("Required terminator missing.", null != requiredTerm && !requiredTerm.getProvider());
        TerminatorService_c reqSvc = TerminatorService_c.getOneD_TSVCOnR1651(requiredTerm,
                (selected) -> "term_service1_new".equals(((TerminatorService_c) selected).getName()));
        assertNotNull("Required terminator service missing.", reqSvc);
        s_dt = DataType_c.getOneS_DTOnR1656(reqSvc);
        assertTrue("Incorrect return type.", null != s_dt && "void".equals(s_dt.getName()));
        TerminatorServiceParameter_c reqSvcParam = TerminatorServiceParameter_c.getOneD_TSPARMOnR1652(reqSvc,
                (selected) -> "enum_param".equals(((TerminatorServiceParameter_c) selected).getName()));
        assertNotNull("Required terminator service parameter missing.", reqSvcParam);
        s_dt = DataType_c.getOneS_DTOnR1653(reqSvcParam);
        assertTrue("Incorrect parameter type.", null != s_dt && "DeploymentsDomain1::MyEnum".equals(s_dt.getName()));
        TerminatorService_c reqSvcStale = TerminatorService_c.getOneD_TSVCOnR1651(requiredTerm,
                (selected) -> "term_service1".equals(((TerminatorService_c) selected).getName()));
        assertTrue("Required terminator service missing.", null != reqSvcStale && reqSvcStale.getIs_stale());
        
        // trigger the removal of stale services
        Selection.getInstance().clear();
        Selection.getInstance().addToSelection(requiredTerm);
        DeleteStaleServicesOnD_TERMAction deleteStaleAction = new DeleteStaleServicesOnD_TERMAction();
        deleteStaleAction.run(null);

        requiredTerm = Terminator_c.getOneD_TERMOnR1650(deployment,
                (selected) -> "DeploymentsDomain1::term1".equals(((Terminator_c) selected).getName()));
        assertTrue("Required terminator missing.", null != requiredTerm && !requiredTerm.getProvider());
        reqSvcStale = TerminatorService_c.getOneD_TSVCOnR1651(requiredTerm,
                (selected) -> "term_service1".equals(((TerminatorService_c) selected).getName()));
        assertNull("Stale service not removed.", reqSvcStale);

    }

    /*
     * Add service
     */
    @Test
    public void testUpdate7() throws Exception {

        // import the terminator and do the basic test
        Deployment_c deployment = basicTest();

        // check that the file exists
        File testFile = new File(getTestModelRespositoryLocation() + String.format(INT_FILE_TEMPLATE, 7));
        assertTrue("Cannot access test file.", testFile.exists());

        // trigger the import action on updated file
        Selection.getInstance().clear();
        Selection.getInstance().addToSelection(deployment);
        ImportTerminatorsFromFileOnD_DEPLAction action = new ImportTerminatorsFromFileOnD_DEPLAction(
                Arrays.asList(new String[] { getTestModelRespositoryLocation() + String.format(INT_FILE_TEMPLATE, 7) })
                        .iterator());
        action.run(null);

        // check the resulting instances
        Terminator_c providedTerm = Terminator_c.getOneD_TERMOnR1650(deployment,
                (selected) -> "DeploymentsDomain1".equals(((Terminator_c) selected).getName()));
        assertTrue("Provided terminator missing.", null != providedTerm && providedTerm.getProvider());
        TerminatorService_c provSvc = TerminatorService_c.getOneD_TSVCOnR1651(providedTerm,
                (selected) -> "public_service1".equals(((TerminatorService_c) selected).getName()));
        assertNotNull("Provided terminator service missing.", provSvc);
        TerminatorServiceParameter_c provSvcParam = TerminatorServiceParameter_c.getOneD_TSPARMOnR1652(provSvc,
                (selected) -> "real_param".equals(((TerminatorServiceParameter_c) selected).getName()));
        assertNotNull("Provided terminator service parameter missing.", provSvcParam);
        DataType_c s_dt = DataType_c.getOneS_DTOnR1653(provSvcParam);
        assertTrue("Incorrect parameter type.", null != s_dt && "real".equals(s_dt.getName()));
        Terminator_c requiredTerm = Terminator_c.getOneD_TERMOnR1650(deployment,
                (selected) -> "DeploymentsDomain1::term1".equals(((Terminator_c) selected).getName()));
        assertTrue("Required terminator missing.", null != requiredTerm && !requiredTerm.getProvider());
        TerminatorService_c reqSvc = TerminatorService_c.getOneD_TSVCOnR1651(requiredTerm,
                (selected) -> "term_service1".equals(((TerminatorService_c) selected).getName()));
        assertNotNull("Required terminator service missing.", reqSvc);
        s_dt = DataType_c.getOneS_DTOnR1656(reqSvc);
        assertTrue("Incorrect return type.", null != s_dt && "void".equals(s_dt.getName()));
        TerminatorServiceParameter_c reqSvcParam = TerminatorServiceParameter_c.getOneD_TSPARMOnR1652(reqSvc,
                (selected) -> "enum_param".equals(((TerminatorServiceParameter_c) selected).getName()));
        assertNotNull("Required terminator service parameter missing.", reqSvcParam);
        s_dt = DataType_c.getOneS_DTOnR1653(reqSvcParam);
        assertTrue("Incorrect parameter type.", null != s_dt && "DeploymentsDomain1::MyEnum".equals(s_dt.getName()));
        TerminatorService_c reqSvc2 = TerminatorService_c.getOneD_TSVCOnR1651(requiredTerm,
                (selected) -> "term_service2".equals(((TerminatorService_c) selected).getName()));
        assertNotNull("Required terminator service missing.", reqSvc2);
        s_dt = DataType_c.getOneS_DTOnR1656(reqSvc2);
        assertTrue("Incorrect return type.", null != s_dt && "void".equals(s_dt.getName()));
        TerminatorServiceParameter_c reqSvcParam2 = TerminatorServiceParameter_c.getOneD_TSPARMOnR1652(reqSvc2,
                (selected) -> "int_param".equals(((TerminatorServiceParameter_c) selected).getName()));
        assertNotNull("Required terminator service parameter missing.", reqSvcParam2);
        s_dt = DataType_c.getOneS_DTOnR1653(reqSvcParam2);
        assertTrue("Incorrect parameter type.", null != s_dt && "integer".equals(s_dt.getName()));

    }

    /*
     * Remove service
     */
    @Test
    public void testUpdate8() throws Exception {

        // import the terminator and do the basic test
        Deployment_c deployment = basicTest();

        // check that the file exists
        File testFile = new File(getTestModelRespositoryLocation() + String.format(INT_FILE_TEMPLATE, 8));
        assertTrue("Cannot access test file.", testFile.exists());

        // trigger the import action on updated file
        Selection.getInstance().clear();
        Selection.getInstance().addToSelection(deployment);
        ImportTerminatorsFromFileOnD_DEPLAction action = new ImportTerminatorsFromFileOnD_DEPLAction(
                Arrays.asList(new String[] { getTestModelRespositoryLocation() + String.format(INT_FILE_TEMPLATE, 8) })
                        .iterator());
        action.run(null);

        // check the resulting instances
        Terminator_c providedTerm = Terminator_c.getOneD_TERMOnR1650(deployment,
                (selected) -> "DeploymentsDomain1".equals(((Terminator_c) selected).getName()));
        assertTrue("Provided terminator missing.", null != providedTerm && providedTerm.getProvider());
        TerminatorService_c provSvc = TerminatorService_c.getOneD_TSVCOnR1651(providedTerm,
                (selected) -> "public_service1".equals(((TerminatorService_c) selected).getName()));
        assertNotNull("Provided terminator service missing.", provSvc);
        TerminatorServiceParameter_c provSvcParam = TerminatorServiceParameter_c.getOneD_TSPARMOnR1652(provSvc,
                (selected) -> "real_param".equals(((TerminatorServiceParameter_c) selected).getName()));
        assertNotNull("Provided terminator service parameter missing.", provSvcParam);
        DataType_c s_dt = DataType_c.getOneS_DTOnR1653(provSvcParam);
        assertTrue("Incorrect parameter type.", null != s_dt && "real".equals(s_dt.getName()));
        Terminator_c requiredTerm = Terminator_c.getOneD_TERMOnR1650(deployment,
                (selected) -> "DeploymentsDomain1::term1".equals(((Terminator_c) selected).getName()));
        assertTrue("Required terminator missing.", null != requiredTerm && !requiredTerm.getProvider());
        TerminatorService_c reqSvc = TerminatorService_c.getOneD_TSVCOnR1651(requiredTerm,
                (selected) -> "term_service1".equals(((TerminatorService_c) selected).getName()));
        assertTrue("Required terminator service missing.", null != reqSvc && reqSvc.getIs_stale());
        
        // trigger the removal of stale services
        Selection.getInstance().clear();
        Selection.getInstance().addToSelection(requiredTerm);
        DeleteStaleServicesOnD_TERMAction deleteStaleAction = new DeleteStaleServicesOnD_TERMAction();
        deleteStaleAction.run(null);

        requiredTerm = Terminator_c.getOneD_TERMOnR1650(deployment,
                (selected) -> "DeploymentsDomain1::term1".equals(((Terminator_c) selected).getName()));
        assertTrue("Required terminator missing.", null != requiredTerm && !requiredTerm.getProvider());
        reqSvc = TerminatorService_c.getOneD_TSVCOnR1651(requiredTerm,
                (selected) -> "term_service1".equals(((TerminatorService_c) selected).getName()));
        assertNull("Stale service not removed.", reqSvc);

    }

    private Deployment_c basicTest() throws Exception {

        // get the deployment
        Deployment_c deployment = Deployment_c.DeploymentInstance(modelRoot);
        assertNotNull(deployment);

        // check that the file exists
        File testFile = new File(getTestModelRespositoryLocation() + BASE_INT_FILE);
        assertTrue("Cannot access test file.", testFile.exists());

        // trigger the import action
        Selection.getInstance().clear();
        Selection.getInstance().addToSelection(deployment);
        ImportTerminatorsFromFileOnD_DEPLAction action = new ImportTerminatorsFromFileOnD_DEPLAction(
                Arrays.asList(new String[] { getTestModelRespositoryLocation() + BASE_INT_FILE }).iterator());
        action.run(null);

        // check the resulting instances
        Terminator_c providedTerm = Terminator_c.getOneD_TERMOnR1650(deployment,
                (selected) -> "DeploymentsDomain1".equals(((Terminator_c) selected).getName()));
        assertTrue("Provided terminator missing.", null != providedTerm && providedTerm.getProvider());
        TerminatorService_c provSvc = TerminatorService_c.getOneD_TSVCOnR1651(providedTerm,
                (selected) -> "public_service1".equals(((TerminatorService_c) selected).getName()));
        assertNotNull("Provided terminator service missing.", provSvc);
        TerminatorServiceParameter_c provSvcParam = TerminatorServiceParameter_c.getOneD_TSPARMOnR1652(provSvc,
                (selected) -> "real_param".equals(((TerminatorServiceParameter_c) selected).getName()));
        assertNotNull("Provided terminator service parameter missing.", provSvcParam);
        DataType_c s_dt = DataType_c.getOneS_DTOnR1653(provSvcParam);
        assertTrue("Incorrect parameter type.", null != s_dt && "real".equals(s_dt.getName()));
        Terminator_c requiredTerm = Terminator_c.getOneD_TERMOnR1650(deployment,
                (selected) -> "DeploymentsDomain1::term1".equals(((Terminator_c) selected).getName()));
        assertTrue("Required terminator missing.", null != requiredTerm && !requiredTerm.getProvider());
        TerminatorService_c reqSvc = TerminatorService_c.getOneD_TSVCOnR1651(requiredTerm,
                (selected) -> "term_service1".equals(((TerminatorService_c) selected).getName()));
        assertNotNull("Required terminator service missing.", reqSvc);
        TerminatorServiceParameter_c reqSvcParam = TerminatorServiceParameter_c.getOneD_TSPARMOnR1652(reqSvc,
                (selected) -> "enum_param".equals(((TerminatorServiceParameter_c) selected).getName()));
        assertNotNull("Required terminator service parameter missing.", reqSvcParam);
        s_dt = DataType_c.getOneS_DTOnR1653(reqSvcParam);
        assertTrue("Incorrect parameter type.", null != s_dt && "DeploymentsDomain1::MyEnum".equals(s_dt.getName()));

        return deployment;
    }

}
