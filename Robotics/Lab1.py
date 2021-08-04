#! /usr/bin/env python
# Dino Anastasopoulos
import rospy
from std_msgs.msg import Empty
from geometry_msgs.msg import Twist

rospy.init_node('cipek')

empty = Empty()
twist = Twist()

takeoff = rospy.Publisher('/ardrone/takeoff', Empty, queue_size=1)
land = rospy.Publisher('/ardrone/land', Empty, queue_size=1)

movement = rospy.Publisher('/cmd_vel', Twist, queue_size=1)

while takeoff.get_num_connections() < 1:
    rospy.loginfo_throttle(2, "Waiting for subscribers on /ardrone/takeoff ..")
    rospy.sleep(0.1)

takeoff.publish(empty)
rospy.sleep(2.0)

twist.linear.x=1.0
movement.publish(twist)
rospy.sleep(3.0)

twist.linear.x=0.0
movement.publish(twist)
rospy.sleep(1.0)

land.publish(empty)
rospy.sleep(1.0)
