INSERT INTO employee (id, name, nik, reporting_manager, job_title, job_level, department_name, division_name, active, mvp_of_the_year)
VALUES
    (1, 'Asep Hidayat', 'NIK001', NULL, 'Lead Backend Engineer', 'Specialist', 'Product Development & Operation', 'Backend Engineering', true, false),
    (2, 'Ayu Lestari', 'NIK002', 1, 'Digital Marketing Specialist', 'Senior', 'Data Management', 'Digital Marketing', true, false),
    (3, 'Budi Santoso', 'NIK003', 1, 'Project Administrator', 'Senior Associate', 'Corporate Strategy', 'Project Management', true, false),
    (4, 'Dian Sari', 'NIK004', 1, 'Lead Frontend Engineer', 'Specialist', 'Product Development & Operation', 'Frontend Engineering', false, false),
    (5, 'Hisyam Maulana', 'NIK005', 1, 'Backend Engineer', 'Associate', 'Product Development & Operation', 'Backend Engineering', false, false);

INSERT INTO three_sixty_review (employee_id, employee_review_id,review_score, review_contribution, review_strength, review_development, type)
VALUES (1, 3,4.2, 'Selalu aktif dalam diskusi tim dan menyumbang ide-ide solutif.','Kemampuan komunikasi dan kolaborasi sangat kuat.', 'Perlu meningkatkan keterampilan teknis di bidang backend.', 'peer'),
       (2, 3,3.8, 'Berpartisipasi baik dalam proyek lintas tim.', 'Mampu menjaga motivasi tim.', 'Butuh pelatihan lebih dalam time management.', 'manager'),
       (3, 2,4.5, 'Mendorong efisiensi kerja tim.', 'Punya kepemimpinan alami.', 'Perlu lebih terbuka terhadap feedback.', 'self'),
       (4, 3,2.1,'Kurang terlibat dalam kegiatan tim dan jarang hadir saat diskusi.', 'Memiliki kemampuan teknis dasar yang cukup.', 'Perlu meningkatkan komunikasi dan kolaborasi dengan rekan kerja.','peer'),
       (5, 2,1.8,'Kontribusi minim dalam sprint terakhir dan kurang inisiatif.','Cukup teliti dalam beberapa tugas administratif.','Butuh pembinaan dalam hal tanggung jawab pekerjaan dan komitmen.', 'manager');

INSERT INTO employee_attendance (employee_id, total_absence, total_sick, total_wfh, total_late_days)
VALUES
      (1, 0, 0, 0, 0),
      (2, 0, 2, 1, 1),
      (3, 1, 13, 0, 15),
      (4, 5, 0, 0, 4),
      (5, 2, 0, 5, 4);


