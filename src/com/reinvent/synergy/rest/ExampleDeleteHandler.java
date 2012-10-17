package com.reinvent.synergy.rest;

import com.reinvent.surus.model.Constants;
import com.reinvent.surus.system.PoolManager;
import com.reinvent.surus.system.TableContext;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.HTableInterface;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Bohdan Mushkevych
 * Description: handles delete requests from UI
 */
public class ExampleDeleteHandler implements RequestHandler {
    protected Logger logger = Logger.getLogger(ExampleDeleteHandler.class.getSimpleName());

    protected HttpServletRequest request;
    protected HttpServletResponse response;

    protected PoolManager pmExample = TableContext.getPoolManager(Constants.TABLE_EXAMPLE);

    protected Integer domainId;
    protected boolean isValid;


    public ExampleDeleteHandler(HttpServletRequest req, HttpServletResponse resp) {
        request = req;
        response = resp;

        try {
            domainId = Integer.valueOf(request.getParameter(PARAMETER_DOMAIN_ID));
            isValid = true;
        } catch (Exception e) {
            logger.warn(String.format("Request is considered invalid (domain_id): (%s)",
                    request.getParameter(PARAMETER_DOMAIN_ID)), e);
            isValid = false;
        }
    }

    public void run() {
        if (!isValid) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        HTableInterface tExample = null;

        try {
            tExample = pmExample.getTable();

            Delete delete = new Delete(Bytes.toBytes(domainId));

            tExample.delete(delete);
            tExample.flushCommits();
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (IOException e) {
            logger.warn(String.format("Exception on deleting Example record (domain): (%s)", domainId), e);
            response.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
        } catch (Exception e) {
            logger.warn(String.format("Exception on deleting Example record (domain): (%s)", domainId), e);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } finally {
            if (tExample != null) {
                pmExample.putTable(tExample);
            }
        }
    }
}
