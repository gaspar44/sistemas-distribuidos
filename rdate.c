#include <stdio.h>
#include <stdlib.h>
#include <rpc/rpc.h>
#include <time.h>
#include "date.h"

int main (int argc, char *argv[]) {
	CLIENT *cl;
	char *server;
	long *lresult;
	long *dResult;
	char **sresult;


	if (argc != 2) {
		fprintf(stderr, "usage: %s hostname\n", argv[0]);
		exit(1);
	}
	server = argv[1];

	if ((cl = clnt_create(server, DATE_PROG, DATE_VERS, "udp")) == NULL) {
		clnt_pcreateerror(server);
		exit(2);
	}
	
	if ((lresult = bin_date_1(NULL, cl)) == NULL)  {
		clnt_perror(cl, server);
		exit(3);
	}

	printf("Fecha Hora sobre el host %s = %ld\n", server, *lresult);
	
	if ((sresult = str_date_1(lresult, cl)) == NULL) {
		clnt_perror(cl, server);
		exit(4);	
	}

	printf("Fecha Hora sobre el host %s = %s\n", server, *sresult);

	long *clientTimeInterval = malloc(sizeof(long));
	*clientTimeInterval = 1636051066L;

	if ((dResult = bin_date_difference_1(clientTimeInterval, cl)) == NULL){
		clnt_perror(cl, server);
		exit(5);
	}

	printf("Fecha Hora sobre el host %s = %ld\n", server, *dResult);

	clnt_destroy(cl);

	return(0);
}