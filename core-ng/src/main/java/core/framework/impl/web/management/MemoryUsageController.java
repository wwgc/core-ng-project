package core.framework.impl.web.management;

import core.framework.api.web.Controller;
import core.framework.api.web.Request;
import core.framework.api.web.Response;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;

/**
 * @author neo
 */
public class MemoryUsageController implements Controller {
    @Override
    public Response execute(Request request) throws Exception {
        ControllerHelper.validateFromLocalNetwork(request.clientIP());
        return Response.bean(memoryUsage());
    }

    private MemoryUsage memoryUsage() {
        MemoryUsage usage = new MemoryUsage();
        MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
        java.lang.management.MemoryUsage heapMemoryUsage = memoryMXBean.getHeapMemoryUsage();
        usage.heapInit = heapMemoryUsage.getInit();
        usage.heapUsed = heapMemoryUsage.getUsed();
        usage.heapCommitted = heapMemoryUsage.getCommitted();
        usage.heapMax = heapMemoryUsage.getMax();

        java.lang.management.MemoryUsage nonHeapMemoryUsage = memoryMXBean.getNonHeapMemoryUsage();
        usage.nonHeapInit = nonHeapMemoryUsage.getInit();
        usage.nonHeapUsed = nonHeapMemoryUsage.getUsed();
        usage.nonHeapCommitted = nonHeapMemoryUsage.getCommitted();
        usage.nonHeapMax = nonHeapMemoryUsage.getMax();

        return usage;
    }
}
