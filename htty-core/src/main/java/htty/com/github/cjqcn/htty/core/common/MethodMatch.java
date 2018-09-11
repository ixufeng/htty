package htty.com.github.cjqcn.htty.core.common;

import htty.com.github.cjqcn.htty.core.http.HttyRequest;
import htty.com.github.cjqcn.htty.core.worker.HttyWorker;

/**
 * @description: Method Match
 * @author: chenjinquan
 * @create: 2018-09-11 23:07
 **/
public interface MethodMatch {

    boolean mathes(HttyRequest httyRequest, HttyWorker httyWorker);
}
