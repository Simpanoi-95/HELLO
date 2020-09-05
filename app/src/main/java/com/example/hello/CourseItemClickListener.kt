package ke.co.hello.activites

import ke.co.hello.models.Course

interface CourseItemClickListener {
    fun onItemClick(course: Course)
}