import time
from random import SystemRandom

from django.contrib.auth.models import User, Group
from django.http import HttpResponse
from prometheus_client import Counter, Gauge, Summary, Histogram, Info, Enum
from rest_framework import viewsets, permissions

from metrics_app.serializers import UserSerializer, GroupSerializer

# Counts... stuff
COUNTER_CALLS_TOTAL = Counter(
    "mainapp_calls_total", "Total number of calls to mainapp", ["some_label"]
)

COUNTER_EXCEPTIONS = Counter("mainapp_exceptions", "Number of exceptions")

# exposes a single value which can be changed in time
# good for measuring in-flight things, e.g. current number of processed tasks
GAUGE_INDEX = Gauge("mainapp_random_gauge", "Mainapp random gauge", ["another_label"])

# create two metrics
# - mainapp_summary_count: number of times this was called
# - mainapp_summary_sum: total sum of values
SUMMARY_INDEX = Summary("mainapp_summary", "Mainapp summary")

# measure values and create a histogram of buckets. Uses generic RPC bucket by default buckets of:
# mainapp_histogram_bucket{le="0.005"} 0.0
# mainapp_histogram_bucket{le="0.01"} 0.0
# mainapp_histogram_bucket{le="0.025"} 0.0
# mainapp_histogram_bucket{le="0.05"} 0.0
# mainapp_histogram_bucket{le="0.075"} 0.0
# mainapp_histogram_bucket{le="0.1"} 0.0
# mainapp_histogram_bucket{le="0.25"} 0.0
# mainapp_histogram_bucket{le="0.5"} 0.0
# mainapp_histogram_bucket{le="0.75"} 0.0
# mainapp_histogram_bucket{le="1.0"} 2.0
# mainapp_histogram_bucket{le="2.5"} 5.0
# mainapp_histogram_bucket{le="5.0"} 9.0
# mainapp_histogram_bucket{le="7.5"} 9.0
# mainapp_histogram_bucket{le="10.0"} 9.0
HISTOGRAM_HST = Histogram("mainapp_histogram", "mainapp_histogram")

# One-time static info.
# Could also NOT be one-time, but... why?
# metric name will have "_info" postfix added to it
INFO = Info("mainapp", "generic mainapp info")
INFO.info({"app_name": "mainapp", "launched_at": str(time.time())})

# Set one state out of many for metric
# Essentially a gauge with one of the possible states
APP_STATES = ["RUNNING", "STOPPED", "IN_PROGRESS"]
ENUM_INDEX = Enum("mainapp_current_state", "main app current state", states=APP_STATES)

RND = SystemRandom()


def index(request):
    COUNTER_CALLS_TOTAL.labels(some_label="some_value").inc()
    GAUGE_INDEX.labels(another_label="value").set(RND.random())
    SUMMARY_INDEX.observe(RND.random())
    ENUM_INDEX.state(RND.choice(APP_STATES))
    return HttpResponse("Hello, world")


def hist(request):
    for i in range(0, 100):
        value = RND.random() * 5
        HISTOGRAM_HST.observe(value)
    return HttpResponse("Hello, historgram")


# This is done automatically
@COUNTER_EXCEPTIONS.count_exceptions()
def ex(request):
    """1/5 requests result in Error which is either NotImplementedError or KeyError"""
    if RND.random() > 0.8:
        if RND.random() > 0.5:
            raise NotImplementedError
        else:
            raise KeyError
    return HttpResponse("You're lucky")


class UserViewSet(viewsets.ModelViewSet):
    """
    API endpoint that allows users to be viewed or edited.
    """

    queryset = User.objects.all().order_by("-date_joined")
    serializer_class = UserSerializer
    permission_classes = [permissions.IsAuthenticated]


class GroupViewSet(viewsets.ModelViewSet):
    """
    API endpoint that allows groups to be viewed or edited.
    """

    queryset = Group.objects.all()
    serializer_class = GroupSerializer
    permission_classes = [permissions.IsAuthenticated]