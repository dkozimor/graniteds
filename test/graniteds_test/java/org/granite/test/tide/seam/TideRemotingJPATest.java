package org.granite.test.tide.seam;

import org.granite.test.tide.seam.entity.Person;
import org.granite.tide.data.DataContext.EntityUpdateType;
import org.granite.tide.invocation.InvocationResult;
import org.junit.Assert;
import org.junit.Test;


public class TideRemotingJPATest extends AbstractTideTestCase {
	
	@Test
    public void testPersistCall() {
        InvocationResult result = invokeComponent("personAction", "create", new Object[] { "joe" });
        
        Assert.assertEquals("Update count", 1, result.getUpdates().length);
        Assert.assertEquals("Update type", EntityUpdateType.PERSIST.name(), result.getUpdates()[0][0]);
        Assert.assertEquals("Update class", Person.class, result.getUpdates()[0][1].getClass());
    }
}
