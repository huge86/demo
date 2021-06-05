package com.pingpukj.tags.up

case class OozieParam(
                       modelId: Long,
                       mainClass: String,
                       jarPath: String,
                       sparkOptions: String,
                       start: String,
                       end: String
                     )
