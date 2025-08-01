INSERT INTO employee (id, name, nik, reporting_manager, job_title, job_level, department_name, division_name, active, mvp_of_the_year)
VALUES
    (1, 'Alfian Purnomo', 'NIK001', NULL, 'Senior Frontend Engineer', 'Senior 2', 'Product Development & Operation', 'Frontend Engineering', true, false),
    (2, 'Fajar Rifai', 'NIK002', 1, 'Backend Engineer', 'Sr. Associate', 'Product Development & Operation', 'Backend Engineer', true, false),
    (3, 'Asep Hidayat', 'NIK003', 2, 'Senior Backend Engineer', 'Senior 2', 'Product Development & Operation', 'Backend Engineer', true, false),
    (4, 'Ayunindya Annistri', 'NIK004', 2, 'Digital Marketing Specialist', 'Specialist 1', 'Data Management', 'Digital Marketing', true, false),
    (5, 'Rizka Indah Mahendiyanti', 'NIK005', 1, 'Project Administrator', 'Sr. Associate', 'Corporate Strategy', 'Project Management', true, false),
    (6, 'Bayu Utomo', 'NIK006', 1, 'System Analyst', 'Senior 1', 'Product Development & Operation', 'Backend Engineering', true, false),
    (7, 'Yosa Kresnandya Diputra', 'NIK007', 3, 'Content Writer', 'Sr. Associate', 'Data Management', 'Digital Marketing', true, false),
    (8, 'Hamal Fauzi', 'NIK008', 4, 'BI Analyst', 'Sr. Associate', 'Corporate Strategy', 'Business Intelligence', true, false),
    (9, 'Mochammad Andri Andaru', 'NIK009', 1, 'Site Reliability Engineer', 'Senior 2', 'Infrastructure', 'Platform Engineering', true, false),
    (10, 'Keyko Nabila', 'NIK010', 5, 'UI Designer', 'Sr. Associate', 'Product Development & Operation', 'Frontend Engineering', true, false),
    (11, 'Hendrawan', 'NIK011', NULL, 'Lead Backend Engineer', 'Specialist 1', 'Product Development & Operation', 'Backend Engineering', true, false),
    (12, 'Ayu Lestari', 'NIK012', 11, 'Digital Marketing Specialist', 'Senior 1', 'Data Management', 'Digital Marketing', true, false),
    (13, 'Budi Santoso', 'NIK013', 11, 'Project Administrator', 'Sr. Associate', 'Corporate Strategy', 'Project Management', true, false),
    (14, 'Dian Sari', 'NIK014', 11, 'Lead Frontend Engineer', 'Specialist 2', 'Product Development & Operation', 'Frontend Engineering', false, false),
    (15, 'Linda Oktaviani', 'NIK015', 13, 'Business Analyst', 'Senior 1', 'Corporate Strategy', 'Project Management', true, false),
    (16, 'Rina Wulandari', 'NIK016', 11, 'UI/UX Designer', 'Sr. Associate', 'Product Development & Operation', 'Frontend Engineering', true, false),
    (17, 'Eko Prasetyo', 'NIK017', 11, 'QA Engineer', 'Senior 2', 'Product Development & Operation', 'Quality Assurance', true, false),
    (18, 'Siti Nurhaliza', 'NIK018', 12, 'Content Strategist', 'Sr. Associate', 'Data Management', 'Digital Marketing', true, false),
    (19, 'Yudi Permana', 'NIK019', 11, 'DevOps Engineer', 'Sr. Associate', 'Infrastructure', 'Platform Engineering', true, false),
    (20, 'Hisyam Maulana', 'NIK020', 11, 'Manager Backend Engineer', 'Manager', 'Product Development & Operation', 'Backend Engineering', true, false);


INSERT INTO three_sixty_review (employee_id, employee_review_id, review_score, review_contribution, review_strength, review_development, type)
VALUES
    (1, 3, 4.5,'Membantu meningkatkan stabilitas sistem backend dengan melakukan refactoring terhadap modul-modul lama yang sebelumnya sulit di-maintain, serta menambahkan layer validasi yang komprehensif.','Kemampuan dalam mendesain database relasional maupun non-relasional sangat baik, dan mampu mengoptimalkan query untuk kebutuhan reporting dan analitik.','Perlu lebih terbuka terhadap kritik teknis serta bersedia melakukan code review secara menyeluruh, bukan hanya dari sisi logika, tetapi juga standar penulisan kode.','self'),
    (2, 3, 3.9,'Sering menjadi penghubung antara tim backend dan DevOps, membantu proses deployment ke environment staging dan production secara efisien serta minim kesalahan.','Menunjukkan pendekatan pemecahan masalah yang logis serta keterampilan komunikasi yang baik dalam menyampaikan kebutuhan teknis kepada anggota tim non-teknis.','Perlu meningkatkan kemampuan dalam menulis automated test dan menerapkan prinsip-prinsip clean architecture yang lebih konsisten dalam setiap modul backend.','manager'),
--     (3, 1, 4.0, 'Optimasi SEO cukup efektif menaikkan traffic.', 'Pemahaman konten marketing baik.', 'Perlu pemahaman teknis lebih dalam.', 'peer'),
    (4, 1, 4.2,'Berperan aktif dalam diskusi teknis lintas tim untuk menentukan skema data serta menyusun strategi caching agar performa backend tetap optimal dalam kondisi traffic tinggi.','Sangat mahir dalam memahami kebutuhan bisnis dan mengubahnya menjadi spesifikasi teknis backend yang jelas dan dapat diimplementasikan secara efektif.','Perlu memperdalam pemahaman mengenai keamanan aplikasi backend, seperti sanitasi input dan pengamanan endpoint terhadap akses tidak sah.','peer'),
    (5, 1, 2.2,'Kontribusi dalam pengembangan fitur backend cenderung terbatas, dan beberapa kali mengalami keterlambatan dalam menyelesaikan modul yang telah ditugaskan.','Meskipun memiliki ide-ide terkait optimalisasi query dan struktur database, ide tersebut jarang dieksekusi atau dikomunikasikan ke tim secara proaktif.','Disarankan untuk meningkatkan kemampuan manajemen waktu dan memperkuat tanggung jawab terhadap task teknis agar tidak menjadi bottleneck.','manager'),
--     (6, 1, 4.6, 'Mampu analisa kebutuhan sistem secara menyeluruh.', 'Struktur berpikir sangat sistematis.', 'Perlu komunikasi yang lebih sederhana.', 'peer'),
--     (7, 2, 3.7, 'Konsisten dalam membuat konten harian.', 'Kreatif dalam storytelling.', 'Perlu eksplorasi tren terbaru.', 'manager'),
--     (8, 1, 4.5, 'Memberikan insight tajam dari data.', 'Kritis dan teliti.', 'Perlu softskill untuk presentasi data.', 'peer'),
--     (9, 2, 4.4, 'Menangani downtime dengan cepat.', 'Sangat tanggap dan sigap.', 'Butuh dokumentasi proses lebih lengkap.', 'manager'),
    (10, 3, 2.1,'Belum menunjukkan peran signifikan dalam proyek backend yang sedang berjalan, dan masih mengalami kesulitan dalam mengikuti standar coding yang berlaku di tim.','Sudah memahami dasar-dasar HTTP dan REST API, namun masih membutuhkan bimbingan untuk dapat menerapkannya dalam proyek nyata secara mandiri.','Sebaiknya mengikuti pelatihan tambahan terkait struktur data, pemrograman berorientasi objek, dan teknik debugging backend untuk mempercepat kurva belajar.','peer');
--     (11, 2, 4.2, 'Selalu aktif dalam diskusi tim dan menyumbang ide-ide solutif.', 'Kemampuan komunikasi dan kolaborasi sangat kuat.', 'Perlu meningkatkan keterampilan teknis di bidang backend.', 'peer');
--     (12, 1, 3.8, 'Berpartisipasi baik dalam proyek lintas tim.', 'Mampu menjaga motivasi tim.', 'Butuh pelatihan lebih dalam time management.', 'manager'),
--     (13, 3, 4.5,'Membantu meningkatkan stabilitas sistem backend dengan melakukan refactoring terhadap modul-modul lama yang sebelumnya sulit di-maintain, serta menambahkan layer validasi yang komprehensif.','Kemampuan dalam mendesain database relasional maupun non-relasional sangat baik, dan mampu mengoptimalkan query untuk kebutuhan reporting dan analitik.','Perlu lebih terbuka terhadap kritik teknis serta bersedia melakukan code review secara menyeluruh, bukan hanya dari sisi logika, tetapi juga standar penulisan kode.','self'),
--     (14, 3, 2.1,'Belum menunjukkan peran signifikan dalam proyek backend yang sedang berjalan, dan masih mengalami kesulitan dalam mengikuti standar coding yang berlaku di tim.','Sudah memahami dasar-dasar HTTP dan REST API, namun masih membutuhkan bimbingan untuk dapat menerapkannya dalam proyek nyata secara mandiri.','Sebaiknya mengikuti pelatihan tambahan terkait struktur data, pemrograman berorientasi objek, dan teknik debugging backend untuk mempercepat kurva belajar.','peer'),
--     (15, 3, 1.8,'Kontribusi terhadap pengembangan backend sangat minim dan seringkali memerlukan supervisi langsung untuk menyelesaikan tugas yang seharusnya bisa dilakukan secara mandiri.','Memiliki perhatian terhadap detail saat membuat dokumentasi teknis sederhana, namun belum menunjukkan kemajuan berarti dalam kemampuan programming backend.','Disarankan untuk mengikuti program mentorship internal agar lebih terbiasa dengan alur kerja backend yang kompleks dan menumbuhkan kepercayaan diri dalam pengambilan keputusan teknis.','manager'),
--     (16, 2, 4.0, 'Memberikan insight bagus untuk desain produk.', 'Kreativitas tinggi dalam UI.', 'Perlu memperdalam pengetahuan UX.', 'peer'),
--     (17, 1, 4.3, 'Menemukan banyak bug kritikal sebelum rilis.', 'Teliti dan proaktif.', 'Perlu lebih kolaboratif dengan tim dev.', 'manager'),
--     (18, 2, 3.7, 'Menulis konten dengan baik.', 'Konsisten dan tekun.', 'Butuh eksplorasi ide baru.', 'manager'),
--     (19, 1, 4.5, 'Menjaga kestabilan sistem saat deployment.', 'Sangat andal dalam DevOps.', 'Perlu belajar komunikasi non-teknis.', 'peer'),
--     (20, 2, 3.9, 'Memberi banyak masukan saat evaluasi proyek.', 'Berpikir analitis baik.', 'Perlu lebih sigap dalam eskalasi risiko.', 'peer');



INSERT INTO employee_attendance (employee_id, total_absence, total_sick, total_wfh, total_late_days)
VALUES
    (1, 0, 1, 2, 0),
    (2, 0, 0, 1, 1),
    (3, 0, 2, 6, 13),
    (4, 0, 0, 0, 0),
    (5, 3, 0, 2, 5),
    (6, 0, 1, 1, 0),
    (7, 0, 0, 4, 1),
    (8, 2, 0, 0, 3),
    (9, 0, 1, 0, 0),
    (10, 1, 0, 2, 1),
    (11, 0, 0, 1, 0),
    (12, 0, 2, 1, 1),
    (13, 1, 13, 0, 15),
    (14, 5, 0, 0, 4),
    (15, 2, 0, 5, 4),
    (16, 0, 1, 2, 0),
    (17, 0, 0, 0, 3),
    (18, 1, 0, 3, 1),
    (19, 0, 2, 0, 2),
    (20, 10, 1, 5, 3);

