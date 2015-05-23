var CourseraApi = function($http, $q, $timeout, $location) {
    this.$http = $http;
    this.$q = $q;
    this.$timeout = $timeout;
    this.$location = $location;
};

angular.module('careerPath').service('courseraApi', CourseraApi);

CourseraApi.prototype.load = function(groupingId) {
    var _this = this;

    var host = this.$location.host();
    var port = this.$location.port();
    var appLocation = "http://" + host + ":" + port;

    var url = appLocation + '/api/coursera?nocCode=' + groupingId
    return this.$http.get(url).then(function(cResponse) {
        return cResponse.data;
    });
};
