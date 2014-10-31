# HttpClient Connection Management

[LogStash][1]

2014-10-28T17:06:19.000Z	
Oct 28 10:06:19 ipapiapp2 org.apache.http.NoHttpResponseException: The target server failed to respond

[Solution][2]

Most likely persistent connections that are kept alive by the connection manager become stale. That is, the target server shuts down the connection on its end without HttpClient being able to react to that event, while the connection is being idle, thus rendering the connection half-closed or 'stale'. Usually this is not a problem. HttpClient employs several techniques to verify connection validity upon its lease from the pool. Even if the stale connection check is disabled and a stale connection is used to transmit a request message the request execution usually fails in the write operation with SocketException and gets automatically retried. However under some circumstances the write operation can terminate without an exception and the subsequent read operation returns -1 (end of stream). In this case HttpClient has no other choice but to assume the request succeeded but the server failed to respond most likely due to an unexpected error on the server side.

The simplest way to remedy the situation is to evict expired connections and connections that have been idle longer than, say, 1 minute from the pool after a period of inactivity. For details please see this section of the HttpClient tutorial.

[Documentation][3]

[1]: http://uplogsh1.ingenuity.com:8083/search#%7B%22offset%22%3A0%2C%22count%22%3A50%2C%22q%22%3A%22%5C%22The%20target%20server%20failed%20to%20respond%5C%22%22%2C%22interval%22%3A3600000%7D
[2]: http://stackoverflow.com/questions/10558791/apache-httpclient-interim-error-nohttpresponseexception
[3]: http://hc.apache.org/httpcomponents-client-ga/tutorial/html/connmgmt.html#d5e659

