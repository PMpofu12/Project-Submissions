#!/usr/bin/env python

# Group Members: Philani Mpofu (1848751) and Dino Anastasopoulos (1900661)
import sys
import copy
import rospy
import moveit_commander
import moveit_msgs.msg
import geometry_msgs.msg


print("============ Starting tutorial setup")
moveit_commander.roscpp_initialize(sys.argv)
rospy.init_node('move_group_python_interface_tutorial',
                anonymous=True)

robot = moveit_commander.RobotCommander()

scene = moveit_commander.PlanningSceneInterface()


print("============ Setting move groups ============")
group_left = moveit_commander.MoveGroupCommander("left_arm")
# group_left.set_planner_id("ESTkConfigDefault")

group_right = moveit_commander.MoveGroupCommander("right_arm")

display_trajectory_publisher = rospy.Publisher('/move_group/display_planned_path',
                                    moveit_msgs.msg.DisplayTrajectory, queue_size=10)


print("============ Left arm reference frame: %s ============" % group_left.get_planning_frame())
print("============ Left arm end effector link: %s ============" % group_left.get_end_effector_link())

print("============ Right arm reference frame: %s ============" % group_right.get_planning_frame())
print("============ Right arm end effector link: %s ============" % group_right.get_end_effector_link())

print("============ Robot Groups: ============")
print(robot.get_group_names())

print("============ Printing robot state ============")
print(robot.get_current_state())
print("============")


print("============ Generating plan_left ============")
group_variable_values_left = group_left.get_current_joint_values()

pose_target_left = geometry_msgs.msg.Pose()
pose_target_left.position.z = 1.5
pose_target_left.position.y = 0.3
pose_target_left.position.x = 0
pose_target_left.orientation.w = 0

group_left.set_pose_target(pose_target_left)
plan_left = group_left.plan()

print("============ Waiting while RVIZ displays plan_left... ============")
rospy.sleep(5)

print("============ Visualizing plan_left ============")
display_trajectory = moveit_msgs.msg.DisplayTrajectory()
display_trajectory.trajectory_start = robot.get_current_state()
display_trajectory.trajectory.append(plan_left)
display_trajectory_publisher.publish(display_trajectory)


print("============ Generating plan_right ============")
group_variable_values_right = group_right.get_current_joint_values()

pose_target_right = geometry_msgs.msg.Pose()
pose_target_right.position.z = 1.5
pose_target_right.position.y = 0.3
pose_target_right.position.x = 0
pose_target_right.orientation.w = 0

group_right.set_pose_target(pose_target_right)
plan_right = group_right.plan()

print("============ Waiting while RVIZ displays plan_right... ============")
rospy.sleep(5)

print("============ Visualizing plan_right ============")
display_trajectory = moveit_msgs.msg.DisplayTrajectory()
display_trajectory.trajectory_start = robot.get_current_state()
display_trajectory.trajectory.append(plan_right)
display_trajectory_publisher.publish(display_trajectory)

print("============ Waiting while plan_left is visualized (again)... ============")
rospy.sleep(5)


print("============ Waiting while plan_right is visualized (again)... ============")
rospy.sleep(5)

group_left.go(wait=True)
group_right.go(wait=True)

#Set left position
left_pos = [-0.8, -1.2, -0.7, 0.6, 1.6, -0.2, 1.6]
for i in range(len(group_variable_values_left)):
    group_variable_values_left[i] = left_pos[i]


group_left.set_joint_value_target(group_variable_values_left)
plan_left = group_left.plan()
group_left.go(wait=True)

#Set right position
right_pos = [0.8, -1.2, 0.7, -0.6, 1.6, 0.2, 1.6]
for i in range(len(group_variable_values_right)):
    group_variable_values_right[i] = right_pos[i]

group_right.set_joint_value_target(group_variable_values_right)
plan_right = group_right.plan()
group_right.go(wait=True)

rospy.sleep(5)
temp = 0
while(temp==0):
	# Left arm
	group_variable_values_left[3] = 0.8
	group_left.set_joint_value_target(group_variable_values_left)
	plan_left = group_left.plan()
	group_left.go(wait=True)


	# Right arm
	group_variable_values_right[3] = -0.8
	group_right.set_joint_value_target(group_variable_values_right)
	plan_right = group_right.plan()
	group_right.go(wait=True)

	# Left arm
	group_variable_values_left[3] = -0.8
	group_left.set_joint_value_target(group_variable_values_left)
	plan_left = group_left.plan()
	group_left.go(wait=True)


	# Right arm
	group_variable_values_right[3] = 0.8
	group_right.set_joint_value_target(group_variable_values_right)
	plan_right = group_right.plan()
	group_right.go(wait=True)


moveit_commander.roscpp_shutdown()
