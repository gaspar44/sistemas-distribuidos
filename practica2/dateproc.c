#include <rpc/rpc.h>
#include "date.h"
#include <time.h>
#include <stdio.h>

long *bin_date_1_svc(void* arg1, struct svc_req *arg2) {
    static long timeval;
    timeval = time((long *)0);
    return(&timeval);
}

char **str_date_1_svc(long *bintime, struct svc_req *arg2) {
    static char *ptr;
    ptr=ctime(bintime);
    return(&ptr);
}

long *bin_date_difference_1_svc(long *clientTime,struct svc_req *arg2) {
    static long timeval;
    timeval = *clientTime - time((long *)0);
    return(&timeval);
}

char **str_date_difference_1_svc(long *clientTime, struct svc_req *arg2){
    static long *timeval;
    timeval = bin_date_difference_1_svc(clientTime,arg2);
    return str_date_1_svc(timeval,arg2);
}