USE [finalproject]
GO

/****** Object:  Table [dbo].[Activity]    Script Date: 2019/10/16 ¤U¤È 06:59:37 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Activity](
	[ActivityID] [int] IDENTITY(1,1) NOT NULL,
	[ActivityTypeName] [nvarchar](20) NOT NULL,
	[ActivityName] [nvarchar](50) NOT NULL,
	[StartDateTime] [datetime] NULL,
	[StartDate] [date] NULL,
	[EndDate] [date] NULL,
	[Location] [nvarchar](50) NOT NULL,
	[NewsTypeID] [int] NOT NULL,
 CONSTRAINT [PK_Activity] PRIMARY KEY CLUSTERED 
(
	[ActivityID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[Activity]  WITH CHECK ADD  CONSTRAINT [FK_Activity_ActivityType] FOREIGN KEY([ActivityTypeName])
REFERENCES [dbo].[ActivityType] ([ActivityTypeName])
GO

ALTER TABLE [dbo].[Activity] CHECK CONSTRAINT [FK_Activity_ActivityType]
GO

ALTER TABLE [dbo].[Activity]  WITH CHECK ADD  CONSTRAINT [FK_Activity_NewsType] FOREIGN KEY([NewsTypeID])
REFERENCES [dbo].[NewsType] ([NewsTypeID])
GO

ALTER TABLE [dbo].[Activity] CHECK CONSTRAINT [FK_Activity_NewsType]
GO


